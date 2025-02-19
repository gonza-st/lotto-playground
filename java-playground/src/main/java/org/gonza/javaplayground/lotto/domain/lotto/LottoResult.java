package org.gonza.javaplayground.lotto.domain.lotto;

import java.util.List;

public class LottoResult {
    private final String lottoId;
    private final List<LottoLineResult> lineResult;

    public LottoResult(String lottoId, List<LottoLineResult> lineResult) {
        validate(lineResult);

        this.lottoId = lottoId;
        this.lineResult = lineResult;
    }

    private void validate(List<LottoLineResult> items) {
        if (items.isEmpty()) {
            throw new IllegalArgumentException("No items found");
        }
    }

    public String getLottoId() {
        return lottoId;
    }

    public List<List<Integer>> getResults() {
        return lineResult.stream()
                .map(LottoLineResult::getResult)
                .toList();
    }
}
