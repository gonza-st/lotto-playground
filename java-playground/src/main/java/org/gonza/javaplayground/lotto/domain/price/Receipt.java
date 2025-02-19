package org.gonza.javaplayground.lotto.domain.price;

import org.gonza.javaplayground.lotto.domain.lotto.LottoLineResult;
import org.gonza.javaplayground.lotto.domain.lotto.LottoResult;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Receipt implements LottoResult {
    private final String id;
    private final List<LottoLineResult> items;

    public Receipt(List<LottoLineResult> items) {
        validate(items);

        this.id = UUID.randomUUID().toString();
        this.items = items;
    }

    private void validate(List<LottoLineResult> items) {
        if (items.isEmpty()) {
            throw new IllegalArgumentException("No items found");
        }
    }

    @Override
    public List<List<Integer>> getResults() {
        return items.stream()
                .map(LottoLineResult::getResult)
                .toList();
    }
}
