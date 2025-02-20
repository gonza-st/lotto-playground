package org.gonza.javaplayground.lotto.domain.lotto;

import java.util.List;

public class LottoResult {
    private final String lottoId;
    private final List<LottoLineResult> lineResult;

    private LottoResult(String lottoId, List<LottoLineResult> lineResult) {
        this.lottoId = lottoId;
        this.lineResult = lineResult;
    }

    public String getLottoId() {
        return lottoId;
    }

    public List<List<Integer>> getResults() {
        return lineResult.stream()
                .map(LottoLineResult::getResult)
                .toList();
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
