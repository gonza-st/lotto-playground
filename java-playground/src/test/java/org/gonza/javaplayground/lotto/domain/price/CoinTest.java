package org.gonza.javaplayground.lotto.domain.price;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CoinTest {

    @Test
    public void should_throw_count_when_create_coin_with_less_than_zero() {
        assertThrows(IllegalArgumentException.class, () -> new Coin(0));
        assertThrows(IllegalArgumentException.class, () -> new Coin(-1));
    }

    @Test
    public void should_throw_when_create_coin_with_null() {
        assertThrows(IllegalArgumentException.class, () -> new Coin(null));
    }
}
