package org.gonza.javaplayground.core;

import java.util.Arrays;

public enum Ranking {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(4, 500_000),
    FOURTH(3, 5_000),
    NONE(2, 0);

    private final int matchCount;
    private final int prize;

    Ranking(int matchCount, int prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public static Ranking valueOf(int matchCount) {
        return Arrays.stream(values())
                .filter(ranking -> ranking.matchCount == matchCount)
                .findFirst()
                .orElse(NONE);
    }
}
