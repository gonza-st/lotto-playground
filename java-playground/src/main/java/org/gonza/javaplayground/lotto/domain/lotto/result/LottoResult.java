package org.gonza.javaplayground.lotto.domain.lotto.result;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoResult {
    private final String lottoId;
    private final List<LottoLineResult> lineResults;

    private LottoResult(String lottoId, List<LottoLineResult> lineResults) {
        this.lottoId = lottoId;
        this.lineResults = lineResults;
    }

    public String getLottoId() {
        return lottoId;
    }

    public Map<Integer, List<LottoLineResult>> getResults() {
        return lineResults.stream()
                .collect(Collectors.groupingBy(LottoLineResult::getMatchedCount));
    }

    public static LottoResult of(String lottoId, List<LottoLineResult> lineResult) {
        validate(lineResult);
        return new LottoResult(lottoId, lineResult);
    }

    private static void validate(List<LottoLineResult> items) {
        if (items.isEmpty()) {
            throw new IllegalArgumentException("No items found");
        }
    }
}
