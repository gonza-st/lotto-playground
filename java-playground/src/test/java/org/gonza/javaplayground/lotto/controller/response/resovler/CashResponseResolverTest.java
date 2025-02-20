package org.gonza.javaplayground.lotto.controller.response.resovler;

import org.gonza.javaplayground.lotto.controller.response.PurchaseRes;
import org.gonza.javaplayground.lotto.ui.adapter.PurchaseUiAdapter;
import org.gonza.javaplayground.lotto.ui.LottoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CashResponseResolverTest {

    private PurchaseUiAdapter sut;

    @BeforeEach
    public void setUp() {
        this.sut = new PurchaseUiAdapter();
    }

    @Nested
    class HandleCashTest {
        @Test
        public void should_return_format_purchase_response() {
            PurchaseRes res = new PurchaseRes(List.of(List.of(1,2,3)));
            LottoResponse lottoResponse = sut.resolve(res);

            String formatted = format(res);
            assertEquals(formatted, lottoResponse.data());
        }

        private String format(PurchaseRes response) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(response.getLottoNumbers().size()).append("개를 구매했습니다.\n");

            response.getLottoNumbers().forEach((lottoNumber) -> {
                stringBuilder.append(lottoNumber).append("\n");
            });

            return stringBuilder.toString().trim();
        }
    }
}
