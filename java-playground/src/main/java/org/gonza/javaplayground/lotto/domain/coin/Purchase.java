package org.gonza.javaplayground.lotto.domain.coin;

import org.gonza.javaplayground.lotto.domain.lotto.Count;

public class Purchase implements Count {
    private final Integer payment;

    private Purchase(Integer payment) {
        this.payment = payment;
    }

    @Override
    public Integer calc(Integer pricePerCount) {
        Integer count = payment / pricePerCount;
        return count;
    }

    public static Purchase of(Integer payment) {
        validatePrice(payment);
        return new Purchase(payment);
    }

    private static void validatePrice(Integer payment) {
        if (payment <= 0) {
            throw new IllegalArgumentException("Payment must be greater than zero");
        }
    }
}
