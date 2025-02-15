package org.gonza.javaplayground.lotto.domain.price;

import java.util.List;

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
        if (point > priceList.size() - 1 || point < 0) {
            throw new IllegalArgumentException("point is out of range");
        }
    }
}
