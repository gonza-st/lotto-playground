package org.gonza.javaplayground.price.domain;

import org.gonza.javaplayground.price.PriceConstant;

public class Price {
    private final Long value;

    private Price(Long price) {
        this.value = price;
    }

    public static Price of(Long price) {
        validatePrice(price);

        return new Price(price);
    }

    public Long divide(Price price) {
        return this.value / price.getValue();
    }

    public Long getValue() {
        return this.value;
    }

    private static void validatePrice(Long price) {
        if (price < PriceConstant.MIN_PRICE) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }
}
