package org.gonza.javaplayground.lotto.controller.response.resolver;

import org.gonza.javaplayground.lotto.controller.response.MatchRes;
import org.gonza.javaplayground.framework.mapper.ResponseResolver;
import org.gonza.javaplayground.lotto.ui.LottoResponse;

public class MatchResponseResolver implements ResponseResolver<MatchRes> {
    @Override
    public LottoResponse resolve(MatchRes response) {
        try {
            Integer status = 200;
            String data = format(response);
            return new LottoResponse(status, data);
        } catch (Exception e) {
            Integer status = 500;
            return new LottoResponse(status, e.getMessage());
        }
    }

    private String format(MatchRes response) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("당첨 통계 \n")
                .append("---------\n");

        response.statistics().forEach((statistics) -> {
            Integer matchCount = statistics.get("winningNumberCount");

            Integer price = statistics.get("price");
            String formattedPrice = String.format("%,d", price);

            Integer count = statistics.get("count");

            stringBuilder
                    .append(matchCount).append("개 일치").append(" ")
                    .append("(").append(formattedPrice).append("원)")
                    .append(" - ").append(count).append("개")
                    .append("\n");
        });

        String formattedProfit = String.format("%.2f",  response.profit());
        stringBuilder.append("총 수익률은 ").append(formattedProfit).append("입니다.");

        return stringBuilder.toString().trim();
    }
}
