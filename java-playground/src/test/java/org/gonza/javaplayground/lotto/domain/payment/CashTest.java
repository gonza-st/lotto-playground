package org.gonza.javaplayground.lotto.domain.payment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CashTest {

    @Test
    public void should_throw_an_exception_when_price_is_out_of_range() {
        Integer negativePrice = -1;

        assertThrows(IllegalArgumentException.class, () -> {
            Cash.of(negativePrice);
        });
    }
}
