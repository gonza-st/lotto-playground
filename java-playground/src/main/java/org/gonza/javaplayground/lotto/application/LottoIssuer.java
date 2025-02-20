package org.gonza.javaplayground.lotto.application;

import org.gonza.javaplayground.lotto.domain.Lotto;
import org.gonza.javaplayground.lotto.domain.LottoNumber;
import org.gonza.javaplayground.lotto.domain.Lottos;
import org.gonza.javaplayground.purchase.domain.Purchase;

import java.util.ArrayList;
import java.util.List;

public class LottoIssuer {
    public static Lottos issue(Purchase purchase) {
        int pages = getLottoPages(purchase);
        List<LottoNumber> lottoNumbers = new ArrayList<>();

        for (int i = 0; i < pages; i++) {
            lottoNumbers.add(RandomLottoNumbers.generate());
        }

        return Lottos.of(lottoNumbers);
    }

    private static int getLottoPages(Purchase purchase) {
        return purchase.getPrice().intValue() / 1_000;
    }
}
