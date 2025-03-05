package org.gonza.javaplayground.lotto.application;

import org.gonza.javaplayground.lotto.LottoConstant;
import org.gonza.javaplayground.lotto.domain.Lottos;
import org.gonza.javaplayground.lotto.domain.Price;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LottoIssuerTest {

    @Test
    void 금액_1000원_당_한_장이다() {
        Price PRICE = Price.of(5000L);

        Lottos lottos = LottoIssuer.issue(PRICE);

        assertEquals(5000 / LottoConstant.LOTTO_PRICE, lottos.getSize());
    }
}