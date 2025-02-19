package org.gonza.javaplayground.lotto.domain.receipt;

import java.util.Map;

public record Item(
        Integer winningNumberCount,
        Integer price,
        Integer count
) {
    public Map<String, Integer> toMap() {
        return Map.of(
                "winningNumberCount", winningNumberCount,
                "price", price,
                "count", count
        );
    }
}
