package org.gonza.javaplayground.lotto.domain;

public class Rank {
    private static final int RANK_COUNT = 7;

    public static Rank of(int matchedCount) {
        return new Rank(RANK_COUNT - matchedCount);
    }

    private final int value;

    public Rank(int value) {
        this.value = value;
    }
}
