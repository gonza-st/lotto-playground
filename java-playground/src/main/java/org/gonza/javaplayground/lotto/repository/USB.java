package org.gonza.javaplayground.lotto.repository;

import org.gonza.javaplayground.lotto.controller.Storage;
import org.gonza.javaplayground.lotto.domain.payment.Cash;
import org.gonza.javaplayground.lotto.domain.lotto.Lotto;

import java.util.*;

public class USB implements Storage {
    private static final Stack<Lotto> lottoFolder = new Stack<>();
    private static final Map<String, Cash> purchaseFolder = new HashMap<>();

    @Override
    public void savePurchaseHistory(Cash cash, Lotto lotto) {
        lottoFolder.push(lotto);
        purchaseFolder.put(lotto.getId(), cash);
    }

    @Override
    public Lotto findRecentLotto() {
        return lottoFolder.peek();
    }

    @Override
    public Cash findLottoPayment(String lottoId) {
        Cash cash = purchaseFolder.getOrDefault(lottoId, null);

        if (Objects.isNull(cash)) {
            throw new IllegalArgumentException("Purchase with id " + lottoId + " not found");
        }

        return cash;
    }
}
