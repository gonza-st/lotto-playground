package org.gonza.javaplayground.lotto.domain.report;

import org.gonza.javaplayground.lotto.domain.lotto.LottoLineResult;
import org.gonza.javaplayground.lotto.domain.lotto.LottoResult;

import java.util.List;
import java.util.Map;

public class ReportFactory {
    private final WinningPrizeTable winningPrizeTable;

    public ReportFactory(WinningPrizeTable winningPrizeTable) {
        this.winningPrizeTable = winningPrizeTable;
    }

    public Report printReport(Payment payment, LottoResult lottoResult) {
        Map<Integer, List<LottoLineResult>> mapByMatchingCount = lottoResult.getResults();

        List<Article> reportArticles = mapByMatchingCount.entrySet().stream()
                .map(this::createReportItem)
                .toList();

        return new Report(lottoResult.getLottoId(), reportArticles, payment.getCost());
    }

    private Article createReportItem(Map.Entry<Integer, List<LottoLineResult>> entry) {
        Integer matchingNumberCount = entry.getKey();
        Integer prize = winningPrizeTable.getWinningPrice(matchingNumberCount);
        Integer resultCount = entry.getValue().size();

        return new Article(matchingNumberCount, prize, resultCount);
    }
}
