package org.gonza.javaplayground.lotto.controller;

import org.gonza.javaplayground.lotto.domain.payment.Cash;
import org.gonza.javaplayground.lotto.domain.lotto.lotto.Lotto;

public interface Storage {
    void savePurchaseHistory(Cash cash, Lotto lotto);

    Lotto findRecentLotto();

    Cash findLottoPayment(String lottoId);
}
