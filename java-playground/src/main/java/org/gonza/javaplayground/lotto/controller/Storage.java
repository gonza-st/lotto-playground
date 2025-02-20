package org.gonza.javaplayground.lotto.controller;

import org.gonza.javaplayground.lotto.domain.payment.Cash;
import org.gonza.javaplayground.lotto.domain.lotto.Lotto;

public interface Storage {
    void savePurchaseHistory(Cash cash, Lotto lotto);

    Lotto findRecentLotto();

    Cash findLottoPurchase(String lottoId);
}
