package org.gonza.javaplayground.lotto.domain.price;

import java.util.Objects;

public class MoneyExchanger {
    private final WinningPriceTable winningPriceTable;

    public MoneyExchanger(WinningPriceTable winningPriceTable) {
        this.winningPriceTable = winningPriceTable;
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
