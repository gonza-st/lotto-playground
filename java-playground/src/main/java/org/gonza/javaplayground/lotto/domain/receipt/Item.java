package org.gonza.javaplayground.lotto.domain.receipt;

import java.util.Map;

public record Item(
        Integer winningNumberCount,
        Integer prize,
        Integer count
) {
    public Map<String, Integer> toMap() {
        return Map.of(
                "winningNumberCount", winningNumberCount,
                "prize", prize,
                "count", count
        );
    }
}
