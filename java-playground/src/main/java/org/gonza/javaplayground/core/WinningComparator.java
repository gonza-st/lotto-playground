package org.gonza.javaplayground.core;

public class WinningComparator {
    public static Ranking compareWinningNumber(LottoNumber lottoNumber, LottoNumber winningLottoNumber) {
        return Ranking.valueOf(lottoNumber.getMatchCount(winningLottoNumber));
    }
}
