package org.gonza.javaplayground.lotto.controller.request.resolver;

import org.gonza.javaplayground.lotto.controller.request.MatchReq;
import org.gonza.javaplayground.lotto.ui.LottoRequest;
import org.gonza.javaplayground.lotto.ui.LottoRequestTestFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class MatchRequestResolverTest {

    private MatchRequestResolver sut;

    @BeforeEach
    public void setUp() {
        this.sut = new MatchRequestResolver();
    }

    @Test
    public void should_separate_number_by_comma() {
        LottoRequest request = LottoRequestTestFixtures.createMatchRequestWithValidBody();
        MatchReq matchReq = sut.resolve(request);

        assertEquals(List.of(1, 2, 3, 4, 5, 6), matchReq.numbers());
    }

    @Test
    public void should_throw_if_string_has_not_number_value() {
        LottoRequest request = LottoRequestTestFixtures.createMatchRequestWithNotNumberValueBody();
        assertThrows(IllegalArgumentException.class, () -> sut.resolve(request));
    }

    @Test
    public void should_throw_if_request_body_is_not_string() {
        LottoRequest request = LottoRequestTestFixtures.createMatchRequestWithIntegerBody();
        assertThrows(ClassCastException.class, () -> sut.resolve(request));
    }

    @Test
    public void should_not_throw_if_request_body_is_empty() {
        LottoRequest request = LottoRequestTestFixtures.createMatchRequestWithEmptyBody();
        assertThrows(NoSuchElementException.class, () -> sut.resolve(request));
    }
}
