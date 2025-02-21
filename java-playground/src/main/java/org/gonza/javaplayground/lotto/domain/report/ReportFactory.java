package org.gonza.javaplayground.lotto.domain.report;

import org.gonza.javaplayground.lotto.domain.lotto.result.LottoLineResult;
import org.gonza.javaplayground.lotto.domain.lotto.result.LottoResultList;
import org.gonza.javaplayground.lotto.domain.lotto.result.LottoResultMap;

import java.util.List;
import java.util.Map;

public class ReportFactory {
    private final WinningPrizeTable winningPrizeTable;

    public ReportFactory(WinningPrizeTable winningPrizeTable) {
        this.winningPrizeTable = winningPrizeTable;
    }

    public Report printReport(Payment payment, LottoResultList lottoResultList) {
        LottoResultMap resultMap = LottoResultMap.from(lottoResultList);

        List<Article> reportArticles = resultMap.result()
                .entrySet().stream()
                .map(this::createReportItem)
                .toList();

        return new Report(lottoResultList.lottoId(), reportArticles, payment.getCost());
    }

    private Article createReportItem(Map.Entry<Integer, List<LottoLineResult>> entry) {
        Integer matchingNumberCount = entry.getKey();
        Integer prize = winningPrizeTable.getWinningPrice(matchingNumberCount);
        Integer resultCount = entry.getValue().size();

        return new Article(matchingNumberCount, prize, resultCount);
    }
}
