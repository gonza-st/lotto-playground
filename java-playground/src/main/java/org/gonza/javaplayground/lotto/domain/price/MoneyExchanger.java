package org.gonza.javaplayground.lotto.domain.price;

import java.util.Objects;

public class MoneyExchanger {
    private final Integer price;
    private final WinningPriceTable winningPriceTable;

    public MoneyExchanger(Integer price, WinningPriceTable winningPriceTable) {
        this.price = price;
        this.winningPriceTable = winningPriceTable;
    }

    public Coin exchangeMoney(Integer money) {
        validatePrice(money);

        Integer amount = money / price;
        return new Coin(amount);
    }

    private void validatePrice(Integer money) {
        if (money <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }
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
