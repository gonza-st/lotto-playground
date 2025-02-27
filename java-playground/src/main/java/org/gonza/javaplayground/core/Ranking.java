package org.gonza.javaplayground.core;

public enum Ranking {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    NONE(0, 0);

    private final int matchCount;
    private final int prize;

    Ranking(int matchCount, int prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public static Ranking valueOf(int matchCount, boolean hasBonusMatch) {
        if (matchCount == 6) return FIRST;
        if (matchCount == 5) return hasBonusMatch ? SECOND : THIRD;
        if (matchCount == 4) return FOURTH;
        if (matchCount == 3) return FIFTH;
        return NONE;
    }

    public int getPrize() {
        return prize;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public String getDescription() {
        if (this == SECOND) {
            return String.format("%d개 일치, 보너스 볼 일치", matchCount);
        }
        if (this != NONE) {
            return String.format("%d개 일치", matchCount);
        }
        return "일치하는 번호 없음";
    }
}
