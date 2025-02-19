package org.gonza.javaplayground.lotto.domain.receipt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReceiptFactoryTest {
    private static final List<Integer> PRICE_LIST = PriceTestFixtures.WINNING_PRICE_RANGE;

    private ReceiptFactory sut;

    @BeforeEach
    public void setUp() {
        WinningPriceTable table = new WinningPriceTable(PRICE_LIST);
        this.sut = new ReceiptFactory(table);
    }

    @Nested
    class WinningPriceTest {
        @Test
        public void should_return_winning_price() {
            Integer matchedPoint = 3;
            Integer winning = sut.getWinningPrice(matchedPoint);
            assertEquals(PRICE_LIST.get(3), winning);
        }

        @Test
        public void should_throw_if_point_is_null() {
            assertThrows(IllegalArgumentException.class, () -> sut.getWinningPrice(null));
        }

        @Test
        public void should_throw_if_point_is_negative_number() {
            assertThrows(IllegalArgumentException.class, () -> sut.getWinningPrice(-1));
        }
    }
}
