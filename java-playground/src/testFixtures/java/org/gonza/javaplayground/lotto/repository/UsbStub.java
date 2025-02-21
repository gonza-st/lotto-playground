package org.gonza.javaplayground.lotto.repository;

import org.gonza.javaplayground.lotto.controller.Storage;
import org.gonza.javaplayground.lotto.domain.lotto.LottoProperties;
import org.gonza.javaplayground.lotto.domain.payment.Cash;
import org.gonza.javaplayground.lotto.domain.lotto.BuyingCount;
import org.gonza.javaplayground.lotto.domain.lotto.lotto.Lotto;
import org.gonza.javaplayground.lotto.domain.lotto.lotto.LottoFactory;
import org.gonza.javaplayground.lotto.domain.utils.RandomNumberGenerator;

public class UsbStub implements Storage {
    @Override
    public void savePurchaseHistory(Cash cash, Lotto lotto) {

    }

    @Override
    public Lotto findRecentLotto() {
        RandomNumberGenerator generator = new RandomNumberGenerator();
        LottoProperties properties = new LottoProperties(1000, 6, 1, 45);
        LottoFactory factory = new LottoFactory(properties, generator);
        BuyingCount coin = Cash.of(1000);
        Lotto lotto = factory.createLotto(coin);

        return lotto;
    }

    @Override
    public Cash findLottoPayment(String lottoId) {
        return Cash.of(1000);
    }
}
