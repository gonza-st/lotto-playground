package org.gonza.javaplayground.lotto.domain.report;

import java.util.Map;

public record Article(
        Integer winningNumberCount,
        Integer prize,
        Integer count,
        Boolean isBonus
) {
    public Map<String, Integer> toMap() {
        Integer isBonus = this.isBonus ? 1 : 0;

        return Map.of(
                "winningNumberCount", winningNumberCount,
                "prize", prize,
                "count", count,
                "isBonus", isBonus
        );
    }
}
