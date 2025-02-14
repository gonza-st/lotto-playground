package org.gonza.javaplayground.lotto.controller.request.resolver;

import org.gonza.javaplayground.lotto.controller.request.PurchaseReq;
import org.gonza.javaplayground.lotto.ui.LottoRequest;
import org.gonza.javaplayground.lotto.ui.LottoRequestTestFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PurchaseRequestResolverTest {

    private PurchaseRequestResolver sut;

    @BeforeEach
    public void setUp() {
        this.sut = new PurchaseRequestResolver();
    }

    @Test
    public void should_throw_if_request_body_is_not_be_able_to_cast_to_string() {
        LottoRequest booleanBody = LottoRequestTestFixtures.createRequestWithBooleanBody();
        assertThrows(ClassCastException.class,
                () -> sut.resolve(booleanBody)
        );
    }

    @Test
    public void should_throw_if_request_body_is_not_number_value() {
        LottoRequest sentenceBody = LottoRequestTestFixtures.createRequestWithNotNumberBody();
        assertThrows(NumberFormatException.class,
                () -> sut.resolve(sentenceBody)
        );
    }

    @Test
    public void should_return_PurchaseRequest_when_body_is_number_value_string() {
        LottoRequest stringBody = LottoRequestTestFixtures.createRequestWithNumberBody();
        PurchaseReq req = sut.resolve(stringBody);

        assertEquals(stringBody.getBody().get(), req.payment().toString());
    }
}
