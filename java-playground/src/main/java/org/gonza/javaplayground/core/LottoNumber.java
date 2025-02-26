package org.gonza.javaplayground.core;

import java.util.List;

public record LottoNumber(List<Integer> lottoNumbers) {
    public static final int MAX_NUMBER = 45;
    public static final int MIN_NUMBER = 1;

    public int getMatchCount(LottoNumber winningNumber) {
        return (int) lottoNumbers.stream()
                .filter(number -> winningNumber.lottoNumbers().contains(number))
                .count();
    }

    public Ranking getRanking(LottoNumber winningNumber, int bonusNumber) {
        int matchCount = getMatchCount(winningNumber);
        boolean hasBonusMatch = lottoNumbers.contains(bonusNumber);

        return Ranking.valueOf(matchCount, hasBonusMatch);
    }
}
