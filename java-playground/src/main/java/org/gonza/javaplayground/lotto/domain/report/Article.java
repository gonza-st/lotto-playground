package org.gonza.javaplayground.lotto.domain.report;

import java.util.Map;

public record Article(
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
