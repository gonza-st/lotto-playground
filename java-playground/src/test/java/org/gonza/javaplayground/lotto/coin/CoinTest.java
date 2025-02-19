package org.gonza.javaplayground.lotto.coin;

import org.gonza.javaplayground.lotto.domain.coin.Coin;
import org.gonza.javaplayground.lotto.domain.lotto.Payment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class CoinTest {

    @Test
    public void should_return_amount() {
        Coin coin = new Coin(1000);
        assertEquals(1000, coin.amount());
    }

    @Test
    public void should_be_instance_of_Payment() {
        Coin coin = new Coin(1000);
        assertInstanceOf(Payment.class, coin);
    }

    @Test
    public void should_be_equals_if_two_coin_has_same_amount() {
        Coin coin1 = new Coin(1000);
        Coin coin2 = new Coin(1000);

        assertEquals(coin1, coin2);
    }
}
