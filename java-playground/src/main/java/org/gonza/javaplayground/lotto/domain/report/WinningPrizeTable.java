package org.gonza.javaplayground.lotto.domain.report;

import java.util.List;

public class WinningPrizeTable {
    private final List<Integer> prizeList;
    private final Integer bonusPrize;

    public WinningPrizeTable(List<Integer> prizeList, Integer bonusPrize) {
        this.prizeList = prizeList;
        this.bonusPrize = bonusPrize;
    }

    public Integer getWinningPrice(Integer point, Boolean isBonus) {
        validate(point);

        if (isBonus) {
            return this.bonusPrize;
        }

        return prizeList.get(point);
    }

    private void validate(Integer point) {
        if (point > prizeList.size() - 1 || point < 0) {
            throw new IllegalArgumentException("point is out of range");
        }
    }
}
