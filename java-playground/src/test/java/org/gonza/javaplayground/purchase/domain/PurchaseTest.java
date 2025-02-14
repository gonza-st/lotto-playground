package org.gonza.javaplayground.purchase.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseTest {

    public static final Price PRICE = Price.of(5_000L);

    @Test
    void 금액을_갖고_생성할_수_있다() {
        Purchase purchase = Purchase.of(PRICE);

        assertInstanceOf(Purchase.class, purchase);
    }
}