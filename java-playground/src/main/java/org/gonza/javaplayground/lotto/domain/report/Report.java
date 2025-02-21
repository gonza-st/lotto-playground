package org.gonza.javaplayground.lotto.domain.report;

import java.util.List;
import java.util.Map;

public class Report {
    private final String lottoId;
    private final List<Article> articles;
    private final Integer cost;

    public Report(String lottoId, List<Article> articles, Integer cost) {
        this.lottoId = lottoId;
        this.articles = articles;
        this.cost = cost;
    }

    public Double getProfit() {
        Double sumOfPrice = articles.stream()
                .mapToDouble(Article::prize)
                .sum();

        if (sumOfPrice == 0.0) {
            return 0.0;
        }

        return sumOfPrice / cost;
    }

    public List<Map<String, Integer>> getStatistics() {
        return articles.stream()
                .map(Article::toMap)
                .toList();
    }

    public String getLottoId() {
        return lottoId;
    }
}
