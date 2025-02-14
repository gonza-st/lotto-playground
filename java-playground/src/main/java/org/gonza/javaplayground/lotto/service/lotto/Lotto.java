package org.gonza.javaplayground.lotto.service.lotto;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Lotto {
    private final String id;
    private final List<LottoLine> lottoLines;

    public Lotto(List<List<Integer>> numbers) {
        if (Objects.isNull(numbers) || numbers.isEmpty()) {
            throw new IllegalArgumentException("The given numbers list is empty");
        }

        this.id = UUID.randomUUID().toString();

        this.lottoLines = numbers.stream()
                .map(LottoLine::new)
                .toList();
    }

    public List<List<Integer>> match(List<Integer> numbers) {
        return lottoLines.stream()
                .map(line -> line.match(numbers))
                .toList();
    }

    public String getId() {
        return id;
    }

    public Integer countLottoLines() {
        return lottoLines.size();
    }
}
