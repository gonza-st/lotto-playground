package org.gonza.javaplayground.lotto.repository;

import org.gonza.javaplayground.lotto.controller.Storage;
import org.gonza.javaplayground.lotto.domain.coin.Purchase;
import org.gonza.javaplayground.lotto.domain.lotto.Count;
import org.gonza.javaplayground.lotto.domain.lotto.Lotto;
import org.gonza.javaplayground.lotto.domain.lotto.LottoFactory;
import org.gonza.javaplayground.lotto.domain.utils.RandomNumberGenerator;

public class UsbStub implements Storage {
    @Override
    public void savePurchaseHistory(Purchase purchase, Lotto lotto) {

    }

    @Override
    public Lotto findRecentLotto() {
        RandomNumberGenerator generator = new RandomNumberGenerator();
        LottoFactory factory = new LottoFactory(generator);
        Count coin = Purchase.of(1000);
        Lotto lotto = factory.createLotto(coin);

        return lotto;
    }

    @Override
    public Purchase findLottoPurchase(String lottoId) {
        return null;
    }
}
