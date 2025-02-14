package org.gonza.javaplayground.lotto.service.domain;

import java.util.List;
import java.util.Objects;

public class Lotto {
    private final List<LottoLine> lottoLines;

    public Lotto(List<List<Integer>> numbers) {
        if (Objects.isNull(numbers) || numbers.isEmpty()) {
            throw new IllegalArgumentException("The given numbers list is empty");
        }

        this.lottoLines = numbers.stream()
                .map(LottoLine::new)
                .toList();
    }

    public List<List<Integer>> match(List<Integer> numbers) {
        return lottoLines.stream()
                .map(line -> line.match(numbers))
                .toList();
    }
}
