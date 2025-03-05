package org.gonza.javaplayground.lotto.application;

import org.gonza.javaplayground.lotto.LottoConstant;
import org.gonza.javaplayground.lotto.domain.Lottos;
import org.gonza.javaplayground.lotto.domain.Price;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

class LottoIssuerTest {

    @Test
    void 금액_1000원_당_한_장이다() {
        Price PRICE = Price.of(5000L);

        Lottos lottos = LottoIssuer.issue(PRICE);

        assertEquals(5000 / LottoConstant.LOTTO_PRICE, lottos.getSize());
    }

    @Test
    void 금액_5000원에_수동_2개_자동_3개를_할_수_있다() {
        Price PRICE = Price.of(5000L);
        List<List<Integer>> manualNumbers = Arrays.asList(
            List.of(1, 2, 3, 4, 5, 6),
            List.of(7, 8, 9, 10, 11, 12)
        );

        Lottos lottos = LottoIssuer.issue(PRICE, manualNumbers);

        assertEquals(5000 / LottoConstant.LOTTO_PRICE, lottos.getSize());
        assertEquals((5000 / LottoConstant.LOTTO_PRICE) - manualNumbers.size(), lottos.getAutomaticSize());
        assertEquals(manualNumbers.size(), lottos.getManualSize());
    }
}