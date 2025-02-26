package org.gonza.javaplayground.purchase.domain;

public class Purchase {

    public static Purchase of(Price price) {
        return new Purchase(price);
    }

    private final Price price;

    private Purchase(Price price) {
        this.price = price;
    }

    public Price getPrice() {
        return this.price;
    }
}
