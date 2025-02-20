package org.gonza.javaplayground.lotto.domain.receipt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningPrizeTableTest {
    private static final List<Integer> PRICE_LIST = PriceTestFixtures.WINNING_PRICE_RANGE;

    private WinningPrizeTable sut;

    @BeforeEach
    public void setUp() {
        this.sut = new WinningPrizeTable(PRICE_LIST);
    }

    @Test
    public void should_return_winning_price() {
        Integer price = sut.getWinningPrice(3);

        assertEquals(PRICE_LIST.get(3), price);
    }

    @Test
    public void should_return_winning_price_when_empty() {
        Integer price = sut.getWinningPrice(0);

        assertEquals(PRICE_LIST.get(0), price);
    }

    @Test
    public void should_return_winning_price_when_max_point() {
        Integer price = sut.getWinningPrice(6);

        assertEquals(PRICE_LIST.get(6), price);
    }

    @Test
    public void should_throw_if_point_is_out_of_range() {
        assertThrows(IllegalArgumentException.class, () -> sut.getWinningPrice(PRICE_LIST.size()));
        assertThrows(IllegalArgumentException.class, () -> sut.getWinningPrice(-1));
    }
}