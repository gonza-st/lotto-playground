package org.gonza.javaplayground.purchase.domain;

public class Price {
    private final Long value;

    public Price(Long price) {
        this.value = price;
    }

    public static Price of(Long price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        return new Price(price);
    }
}
