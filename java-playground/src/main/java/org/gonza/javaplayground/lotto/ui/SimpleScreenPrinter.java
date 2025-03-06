package org.gonza.javaplayground.lotto.ui;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class SimpleScreenPrinter {
    private static final Integer MIN_MATCHING_COUNT = 3;

    private final PrintWriter printer;

    public SimpleScreenPrinter(PrintWriter out) {
        this.printer = out;
    }

    public void showBuyingNumbers(List<List<Integer>> numbers) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(numbers.size()).append("개를 구매했습니다.\n");

        numbers.forEach((lottoNumber) -> {
            stringBuilder.append(lottoNumber).append("\n");
        });

        String format = stringBuilder.toString().trim();

        printer.println(format);
    }

    public void showReport(
            Double profit,
            List<Map<String, Integer>> allStatistics
    ) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("당첨 통계 \n")
                .append("---------\n");

        allStatistics.stream()
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

        String formattedProfit = String.format("%.2f",  profit);
        stringBuilder.append("총 수익률은 ").append(formattedProfit).append("입니다.");

        String format = stringBuilder.toString().trim();
        printer.println(format);
    }
}
