package org.gonza.javaplayground.lotto.application;

import org.gonza.javaplayground.lotto.LottoConstant;
import org.gonza.javaplayground.lotto.domain.LottoNumbers;
import org.gonza.javaplayground.lotto.domain.Lottos;
import org.gonza.javaplayground.lotto.domain.Price;

import java.util.ArrayList;
import java.util.List;

public class LottoIssuer {

    public static Lottos issue(Price price) {
        int pages = getLottoPages(price);
        List<LottoNumbers> lottoNumbers = new ArrayList<>();

        for (int i = 0; i < pages; i++) {
            lottoNumbers.add(RandomLottoNumbers.generate());
        }

        return Lottos.of(lottoNumbers);
    }

    private static int getLottoPages(Price price) {
        Price lottoPrice = Price.of(LottoConstant.LOTTO_PRICE);
        return price.divide(lottoPrice)
                .intValue();
    }
}
