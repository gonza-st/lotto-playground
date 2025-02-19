package org.gonza.javaplayground.lotto.domain.lotto;

import java.util.List;
import java.util.UUID;

public class LottoResult {
    private final String id;
    private final List<LottoLineResult> lineResult;

    public LottoResult(List<LottoLineResult> lineResult) {
        validate(lineResult);

        this.id = UUID.randomUUID().toString();
        this.lineResult = lineResult;
    }

    private void validate(List<LottoLineResult> items) {
        if (items.isEmpty()) {
            throw new IllegalArgumentException("No items found");
        }
    }

    public List<List<Integer>> getResults() {
        return lineResult.stream()
                .map(LottoLineResult::getResult)
                .toList();
    }
}
