package org.gonza.javaplayground.lotto.domain.payment;

import org.gonza.javaplayground.lotto.domain.lotto.Count;

public class Cash implements Count {
    private final Integer payment;

    private Cash(Integer payment) {
        this.payment = payment;
    }

    @Override
    public Integer calc(Integer pricePerCount) {
        Integer count = payment / pricePerCount;
        return count;
    }

    public static Cash of(Integer payment) {
        validatePrice(payment);
        return new Cash(payment);
    }

    private static void validatePrice(Integer payment) {
        if (payment <= 0) {
            throw new IllegalArgumentException("Payment must be greater than zero");
        }
    }
}
