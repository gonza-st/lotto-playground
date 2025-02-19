package org.gonza.javaplayground.lotto.domain.lotto;

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
                .map(LottoLine::of)
                .toList();
    }

    public LottoResult match(LottoLine candidate) {
        List<LottoLineResult> results =  lottoLines.stream()
                .map(line -> line.match(candidate))
                .toList();

        return new LottoResult(id, results);
    }

    public String getId() {
        return id;
    }

    public Integer countLottoLines() {
        return lottoLines.size();
    }

    public List<List<Integer>> getAllLottoNumbers() {
        return lottoLines.stream()
                .map(LottoLine::getAllNumbers)
                .toList();
    }
}
