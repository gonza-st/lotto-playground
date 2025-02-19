package org.gonza.javaplayground.lotto.controller;

import org.gonza.javaplayground.lotto.domain.coin.Purchase;
import org.gonza.javaplayground.lotto.domain.lotto.Lotto;

public interface Storage {
    void savePurchaseHistory(Purchase purchase, Lotto lotto);

    Lotto readLast();
}
