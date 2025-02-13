package org.gonza.javaplayground.core;

import java.math.BigDecimal;

public class LottoMachine {

    public static final int PURCHASE_PRICE_UNIT = 1000;

    public int generateLottoTicket(Money purchasePrice) {
        if (purchasePrice.getValue().compareTo(BigDecimal.valueOf(PURCHASE_PRICE_UNIT)) < 0) {
            throw new IllegalArgumentException("로또는 1000원 이상부터 구매할 수 있습니다.");
        }

        int value = purchasePrice.getValue()
                .divide(BigDecimal.valueOf(PURCHASE_PRICE_UNIT))
                .intValue();

        if (purchasePrice.getValue().remainder(BigDecimal.valueOf(PURCHASE_PRICE_UNIT)).compareTo(BigDecimal.ZERO) != 0) {
            throw new IllegalArgumentException("로또 구매 금액은 1000원 단위여야 합니다.");
        }

        return value;
    }
}
