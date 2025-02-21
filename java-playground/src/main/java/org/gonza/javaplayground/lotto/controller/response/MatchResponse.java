package org.gonza.javaplayground.lotto.controller.response;

import java.util.List;
import java.util.Map;

public record MatchResponse(
        String id,
        Double profit,
        List<Map<String, Integer>> statistics
) {
}
