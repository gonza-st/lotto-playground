package org.gonza.javaplayground.core;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WinningStatistics {
    private final Map<Ranking, Integer> rankingCounts;
    private final int totalTicketCount;

    public WinningStatistics(List<LottoNumber> purchasedNumbers, LottoNumber winningNumber, LottoNumber bonusNumber) {
        rankingCounts = initializeRankingCounts();
        countWinnings(purchasedNumbers, winningNumber, bonusNumber);
        this.totalTicketCount = purchasedNumbers.size();
    }

    public double calculateReturnRate() {
        int totalPrize = rankingCounts.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrize() * entry.getValue())
                .sum();

        return (double) totalPrize / (totalTicketCount * 1000);
    }

    public int getCountByRanking(Ranking ranking) {
        return rankingCounts.get(ranking);
    }

    private Map<Ranking, Integer> initializeRankingCounts() {
        return Arrays.stream(Ranking.values())
                .collect(Collectors.toMap(
                        ranking -> ranking,
                        ranking -> 0
                ));
    }

    private void countWinnings(List<LottoNumber> purchasedNumbers, LottoNumber winningNumber, LottoNumber bonusNumber) {
        purchasedNumbers.forEach(purchasedNumber -> {
            Ranking ranking = purchasedNumber.getRanking(winningNumber, bonusNumber);
            rankingCounts.merge(ranking, 1, Integer::sum);
        });
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("당첨 통계\n");
        sb.append("---------\n");

        Arrays.stream(Ranking.values())
                .filter(ranking -> ranking != Ranking.NONE)
                .forEach(ranking ->
                        sb.append(String.format("%s (%,d원)- %d개\n",
                                ranking.getDescription(),
                                ranking.getPrize(),
                                rankingCounts.get(ranking))));

        sb.append(String.format("총 수익률은 %.2f입니다.", calculateReturnRate()));

        return sb.toString();
    }

}
