package org.gonza.javaplayground.lotto.domain.payment;

import org.gonza.javaplayground.lotto.domain.lotto.BuyingCount;
import org.gonza.javaplayground.lotto.domain.report.Payment;

public class Cash implements BuyingCount, Payment {
    private final Integer payment;

    private Cash(Integer payment) {
        this.payment = payment;
    }

    @Override
    public Integer calculate(Integer pricePerCount) {
        Integer count = payment / pricePerCount;
        return count;
    }

    @Override
    public Integer getCost() {
        return payment;
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
