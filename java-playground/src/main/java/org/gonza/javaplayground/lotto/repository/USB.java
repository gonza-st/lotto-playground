package org.gonza.javaplayground.lotto.repository;

import org.gonza.javaplayground.lotto.controller.Storage;
import org.gonza.javaplayground.lotto.domain.coin.Purchase;
import org.gonza.javaplayground.lotto.domain.lotto.Lotto;

import java.util.*;

public class USB implements Storage {
    private static final Stack<Lotto> lottoFolder = new Stack<>();
    private static final Map<String, Purchase> purchaseFolder = new HashMap<>();

    @Override
    public void savePurchaseHistory(Purchase purchase, Lotto lotto) {
        lottoFolder.push(lotto);
        purchaseFolder.put(lotto.getId(), purchase);
    }

    @Override
    public Lotto readLast() {
        return lottoFolder.peek();
    }
}
