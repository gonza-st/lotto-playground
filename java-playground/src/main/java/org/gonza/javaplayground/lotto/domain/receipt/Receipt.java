package org.gonza.javaplayground.lotto.domain.receipt;

import java.util.List;
import java.util.Map;

public class Receipt {
    private final String lottoId;
    private final List<Item> items;
    private final Integer cost;

    public Receipt(String lottoId, List<Item> items, Integer cost) {
        this.lottoId = lottoId;
        this.items = items;
        this.cost = cost;
    }

    public Double getProfit() {
        Double sumOfPrice = items.stream()
                .mapToDouble(Item::price)
                .sum();

        return cost / sumOfPrice;
    }

    public List<Map<String, Integer>> getStatistics() {
        return items.stream().map(Item::toMap).toList();
    }
}
