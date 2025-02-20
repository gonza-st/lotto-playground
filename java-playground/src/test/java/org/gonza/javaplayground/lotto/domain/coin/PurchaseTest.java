package org.gonza.javaplayground.lotto.domain.coin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PurchaseTest {

    @Test
    public void should_throw_an_exception_when_price_is_out_of_range() {
        Integer negativePrice = -1;

        assertThrows(IllegalArgumentException.class, () -> {
            Purchase.of(negativePrice);
        });
    }
}
