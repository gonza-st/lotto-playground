package org.gonza.javaplayground.lotto.domain.report;

import java.util.List;

public class WinningPrizeTable {
    private final List<Integer> prizeList;

    public WinningPrizeTable(List<Integer> prizeList) {
        this.prizeList = prizeList;
    }

    public Integer getWinningPrice(Integer point) {
        validate(point);

        return prizeList.get(point);
    }

    private void validate(Integer point) {
        if (point > prizeList.size() - 1 || point < 0) {
            throw new IllegalArgumentException("point is out of range");
        }
    }
}
