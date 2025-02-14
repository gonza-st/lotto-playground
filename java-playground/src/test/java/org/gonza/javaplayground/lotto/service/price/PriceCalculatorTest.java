package org.gonza.javaplayground.lotto.service.price;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PriceCalculatorTest {
    private static final Integer PRICE = PriceTestFixtures.PRICE;
    private static final List<Integer> PRICE_LIST = PriceTestFixtures.WINNING_PRICE_RANGE;

    private PriceCalculator sut;

    @BeforeEach
    public void setUp() {
        WinningPriceTable table = new WinningPriceTable(PRICE_LIST);
        this.sut = new PriceCalculator(PRICE, table);
    }

    @Nested
    class GetAvailableAmountTest {
        @Test
        public void should_return_amount_when_price_is_provided() {
            Integer price = 14000;
            Integer amount = sut.getAvailableAmount(price);
            assertEquals(14, amount);
        }

        @Test
        public void should_return_max_amount_when_change_exists() {
            Integer price = 3500;
            Integer amount = sut.getAvailableAmount(price);
            assertEquals(3, amount);
        }

        @Test
        public void should_throw_an_exception_when_price_is_out_of_range() {
            Integer negativePrice = -1;

            assertThrows(IllegalArgumentException.class, () -> {
                sut.getAvailableAmount(negativePrice);
            });
        }
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
