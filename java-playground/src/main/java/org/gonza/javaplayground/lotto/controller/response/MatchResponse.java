package org.gonza.javaplayground.lotto.controller.response;

import java.util.List;
import java.util.Map;

public class MatchResponse {
    private final Double profit;
    private final StatisticsResponse statistics;

    public MatchResponse(
            Double profit,
            List<Map<String, Integer>> statistics
    ) {
        this.profit = profit;
        this.statistics = new StatisticsResponse(statistics);
    }

    public Double getProfit() {
        return profit;
    }

    public List<Map<String, Integer>> getStatistics() {
        return statistics.statistics();
    }

    private record StatisticsResponse(
            List<Map<String, Integer>> statistics
    ) { }
}