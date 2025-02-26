package org.gonza.javaplayground.lotto.domain.report;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReportTest {

    private static final String id = UUID.randomUUID().toString();

    @Nested
    class ProfitTest {
        @Test
        public void should_return_profit() {
            List<Article> articles = List.of(
                    new Article(0, 500, 1)
            );
            Integer cost = 1000;

            Report report = new Report(id, articles, cost);

            assertEquals(0.5, report.getProfit());
        }

        @Test
        public void should_return_if_no_winning_items_exist() {
            List<Article> articles = List.of(
                    new Article(0, 0, 1)
            );
            Integer cost = 100;

            Report report = new Report(id, articles, cost);

            assertEquals(0.0, report.getProfit());
        }
    }
}
