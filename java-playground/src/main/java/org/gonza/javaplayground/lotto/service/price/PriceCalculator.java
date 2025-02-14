package org.gonza.javaplayground.lotto.service.price;

import java.util.Objects;

public class PriceCalculator {
    private final Integer price;
    private final WinningPriceTable winningPriceTable;

    public PriceCalculator(Integer price, WinningPriceTable winningPriceTable) {
        this.price = price;
        this.winningPriceTable = winningPriceTable;
    }

    public Integer getAvailableAmount(Integer givenPrice) {
        Integer amount = givenPrice / price;
        return amount;
    }

    public Integer getWinningPrice(Integer point) {
        validate(point);

        return winningPriceTable.getWinningPrice(point);
    }

    private void validate(Integer point) {
        if (Objects.isNull(point)) {
            throw new IllegalArgumentException("point cannot be null");
        }

        if (point < 0) {
            throw new IllegalArgumentException("point is less than zero");
        }
    }
}
