package org.gonza.javaplayground.lotto.repository;

import org.gonza.javaplayground.lotto.controller.Storage;
import org.gonza.javaplayground.lotto.domain.coin.Purchase;
import org.gonza.javaplayground.lotto.domain.lotto.Lotto;
import org.gonza.javaplayground.lotto.domain.lotto.LottoFactory;
import org.gonza.javaplayground.lotto.domain.coin.Coin;
import org.gonza.javaplayground.lotto.domain.utils.RandomNumberGenerator;

public class UsbStub implements Storage {
    @Override
    public void savePurchaseHistory(Purchase purchase, Lotto lotto) {

    }

    @Override
    public Lotto readLast() {
        RandomNumberGenerator generator = new RandomNumberGenerator();
        LottoFactory factory = new LottoFactory(generator);
        Coin coin = new Coin(1000);
        Lotto lotto = factory.createLotto(coin);

        return lotto;
    }
}
