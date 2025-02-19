package org.gonza.javaplayground.lotto.domain.lotto;

import java.util.List;
import java.util.UUID;

public class LottoResult {
    private final String id;
    private final List<LottoLineResult> items;

    public LottoResult(List<LottoLineResult> items) {
        validate(items);

        this.id = UUID.randomUUID().toString();
        this.items = items;
    }

    private void validate(List<LottoLineResult> items) {
        if (items.isEmpty()) {
            throw new IllegalArgumentException("No items found");
        }
    }

    public List<List<Integer>> getResults() {
        return items.stream()
                .map(LottoLineResult::getResult)
                .toList();
    }
}
