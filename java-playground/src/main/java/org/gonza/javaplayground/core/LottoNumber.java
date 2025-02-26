package org.gonza.javaplayground.core;

import java.util.List;

public record LottoNumber(List<Integer> lottoNumbers) {
    public int getMatchCount(LottoNumber winningNumber) {
        return (int) lottoNumbers.stream()
                .filter(number -> winningNumber.lottoNumbers().contains(number))
                .count();
    }

    public Ranking getRanking(LottoNumber winningNumber) {
        return Ranking.valueOf(getMatchCount(winningNumber));
    }
}
