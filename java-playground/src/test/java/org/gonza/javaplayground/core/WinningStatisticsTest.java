package org.gonza.javaplayground.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class WinningStatisticsTest {

    @Test
    @DisplayName("당첨 통계를 생성할 수 있다.")
    void createWinningStatisticsSuccessTest() throws Exception {

        //given
        LottoNumber lottoNumber1 = new LottoNumber(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber lottoNumber2 = new LottoNumber(List.of(13, 12, 33, 43, 5, 23));
        List<LottoNumber> purchasedNumbers = List.of(lottoNumber1, lottoNumber2);
        LottoNumber winningLottoNumber = new LottoNumber(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusLottoNumber = LottoNumber.createBonusLottoNumber(23);

        //when
        WinningStatistics winningStatistics = new WinningStatistics(purchasedNumbers, winningLottoNumber, bonusLottoNumber);

        //then
        assertAll(
                () -> assertThat(winningStatistics.getCountByRanking(Ranking.FIRST)).isEqualTo(1),
                () -> assertThat(winningStatistics.getCountByRanking(Ranking.SECOND)).isEqualTo(0),
                () -> assertThat(winningStatistics.getCountByRanking(Ranking.THIRD)).isEqualTo(0),
                () -> assertThat(winningStatistics.getCountByRanking(Ranking.FOURTH)).isEqualTo(0),
                () -> assertThat(winningStatistics.getCountByRanking(Ranking.NONE)).isEqualTo(1)
        );
    }

    @Test
    @DisplayName("수익률을 계산할 수 있다.")
    void calculateReturnRateTest() throws Exception {
        //given
        LottoNumber lottoNumber1 = new LottoNumber(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber lottoNumber2 = new LottoNumber(List.of(1, 2, 3, 4, 5, 7));
        LottoNumber lottoNumber3 = new LottoNumber(List.of(1, 2, 3, 4, 5, 10));
        List<LottoNumber> purchasedNumbers = List.of(lottoNumber1, lottoNumber2, lottoNumber3);

        LottoNumber winningLottoNumber = new LottoNumber(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = LottoNumber.createBonusLottoNumber(10);


        int totalPurchaseAmount = purchasedNumbers.size() * 1000;
        int expectedFirstPrizeMoney = Ranking.FIRST.getPrize();
        int expectedSecondPrizeMoney = Ranking.SECOND.getPrize();
        int expectedThirdPrizeMoney = Ranking.THIRD.getPrize();

        int totalPrizeMoney = expectedFirstPrizeMoney + expectedSecondPrizeMoney + expectedThirdPrizeMoney;

        double expectedReturnRate = (double) totalPrizeMoney / totalPurchaseAmount;

        //when
        WinningStatistics winningStatistics = new WinningStatistics(purchasedNumbers, winningLottoNumber, bonusNumber);

        //then
        assertThat(winningStatistics.calculateReturnRate()).isEqualTo(expectedReturnRate);
    }

    @Test
    @DisplayName("당첨되지 않은 경우 수익률은 0이다.")
    void calculateReturnRateWithNoWinningTest() throws Exception {
        //given
        LottoNumber lottoNumber = new LottoNumber(List.of(7, 8, 9, 10, 11, 12));
        List<LottoNumber> purchasedNumbers = List.of(lottoNumber);
        LottoNumber winningLottoNumber = new LottoNumber(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = LottoNumber.createBonusLottoNumber(23);


        //when
        WinningStatistics winningStatistics = new WinningStatistics(purchasedNumbers, winningLottoNumber, bonusNumber);

        //then
        assertThat(winningStatistics.calculateReturnRate()).isZero();
    }

    @ParameterizedTest
    @DisplayName("등수별 당첨 횟수를 정확히 계산한다")
    @CsvSource({
            "1, 2, 3, 4, 5, 6, FIRST",
            "1, 2, 3, 4, 5, 7, THIRD",
            "1, 2, 3, 4, 5, 10, SECOND",
            "1, 2, 3, 4, 7, 8, FOURTH",
            "1, 2, 3, 7, 8, 9, FIFTH",
            "1, 2, 7, 8, 9, 10, NONE"
    })
    void countWinningsByRankingTest(
            int n1,
            int n2,
            int n3,
            int n4,
            int n5,
            int n6,
            Ranking expectedRanking
    ) {
        //given
        LottoNumber lottoNumber = new LottoNumber(List.of(n1, n2, n3, n4, n5, n6));
        List<LottoNumber> purchasedNumbers = List.of(lottoNumber);
        LottoNumber winningLottoNumber = new LottoNumber(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = LottoNumber.createBonusLottoNumber(10);

        //when
        WinningStatistics winningStatistics = new WinningStatistics(purchasedNumbers, winningLottoNumber, bonusNumber);

        //then
        assertThat(winningStatistics.getCountByRanking(expectedRanking)).isEqualTo(1);

        Arrays.stream(Ranking.values())
                .filter(ranking -> ranking != expectedRanking)
                .forEach(ranking ->
                        assertThat(winningStatistics.getCountByRanking(ranking)).isEqualTo(0));
    }

}
