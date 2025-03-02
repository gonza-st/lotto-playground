package org.gonza.javaplayground.lotto.domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0),
    ;

    private int countOfMatch;
    private int winningMoney;

    Rank(int countOfMatch, int winningMoney) {
        this.countOfMatch = countOfMatch;
        this.winningMoney = winningMoney;
    }

    public static Rank valueOf(int matchedCount, boolean matchedBonus) {
        if (matchedCount == 5 && matchedBonus) {
            return SECOND;
        }

        return Arrays.stream(values())
            .filter(rank -> rank != SECOND && rank.countOfMatch == matchedCount)
            .findFirst()
            .orElse(MISS);
    }
}
