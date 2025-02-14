package org.gonza.javaplayground.lotto.service.price;

import java.util.List;
import java.util.Objects;

public class WinningPriceTable {
    private final List<Integer> priceList;

    public WinningPriceTable(List<Integer> priceList) {
        this.priceList = priceList;
    }

    public Integer getWinningPrice(Integer point) {
        validate(point);

        return priceList.get(point);
    }

    private void validate(Integer point) {
        if (Objects.isNull(point)) {
            throw new IllegalArgumentException("Matched count cannot be null");
        }

        if (point > priceList.size() - 1) {
            throw new IllegalArgumentException("Matched count is greater than winning price");
        }

        if (point < 0) {
            throw new IllegalArgumentException("Matched count is less than zero");
        }
    }

}
