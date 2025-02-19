package org.gonza.javaplayground.lotto.domain.coin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoinFactoryTest {

    private CoinFactory sut;

    @BeforeEach
    public void setUp() {
        Integer price = 1_000;
        this.sut = new CoinFactory(price);
    }

    @Test
    public void should_return_amount_when_price_is_provided() {
        Purchase purchase = new Purchase(14000);
        Coin coin = sut.issueCoin(purchase);
        assertEquals(14, coin.count());
    }

    @Test
    public void should_return_max_amount_when_change_exists() {
        Purchase purchase = new Purchase(3500);
        Coin coin = sut.issueCoin(purchase);
        assertEquals(3, coin.count());
    }
}
