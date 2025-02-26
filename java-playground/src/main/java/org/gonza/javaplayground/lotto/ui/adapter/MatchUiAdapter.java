package org.gonza.javaplayground.lotto.ui.adapter;

import org.gonza.javaplayground.lotto.controller.response.MatchResponse;
import org.gonza.javaplayground.framework.mapper.ResponseResolver;

public class MatchUiAdapter extends ResponseResolver<MatchResponse> {
    private static final Integer MIN_MATCHING_COUNT = 3;

    @Override
    protected String format(MatchResponse response) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("당첨 통계 \n")
                .append("---------\n");

        response.getStatistics().stream()
                .filter((statistics) -> statistics.get("winningNumberCount") >= MIN_MATCHING_COUNT)
                .forEach((statistics) -> {
            Integer matchCount = statistics.get("winningNumberCount");

            Integer price = statistics.get("prize");
            String formattedPrice = String.format("%,d", price);

            Integer count = statistics.get("count");

            stringBuilder
                    .append(matchCount).append("개 일치").append(" ")
                    .append("(").append(formattedPrice).append("원)")
                    .append(" - ").append(count).append("개")
                    .append("\n");
        });

        String formattedProfit = String.format("%.2f",  response.getProfit());
        stringBuilder.append("총 수익률은 ").append(formattedProfit).append("입니다.");

        return stringBuilder.toString().trim();
    }
}
