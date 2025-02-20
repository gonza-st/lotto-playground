package org.gonza.javaplayground.lotto.repository;

import org.gonza.javaplayground.lotto.controller.Storage;
import org.gonza.javaplayground.lotto.domain.payment.Cash;
import org.gonza.javaplayground.lotto.domain.lotto.Count;
import org.gonza.javaplayground.lotto.domain.lotto.Lotto;
import org.gonza.javaplayground.lotto.domain.lotto.LottoFactory;
import org.gonza.javaplayground.lotto.domain.utils.RandomNumberGenerator;

public class UsbStub implements Storage {
    @Override
    public void savePurchaseHistory(Cash cash, Lotto lotto) {

    }

    @Override
    public Lotto findRecentLotto() {
        RandomNumberGenerator generator = new RandomNumberGenerator();
        LottoFactory factory = new LottoFactory(generator);
        Count coin = Cash.of(1000);
        Lotto lotto = factory.createLotto(coin);

        return lotto;
    }

    @Override
    public Cash findLottoPurchase(String lottoId) {
        return null;
    }
}
