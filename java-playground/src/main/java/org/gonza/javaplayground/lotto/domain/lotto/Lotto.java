package org.gonza.javaplayground.lotto.domain.lotto;

import org.gonza.javaplayground.lotto.domain.lotto.result.LottoLineResult;
import org.gonza.javaplayground.lotto.domain.lotto.result.LottoResult;

import java.util.List;
import java.util.UUID;

public class Lotto {
    private final String id;
    private final List<LottoLine> lottoLines;

    public Lotto(List<List<Integer>> numbers) {
        this.id = UUID.randomUUID().toString();
        this.lottoLines = numbers.stream()
                .map(LottoLine::new)
                .toList();
    }

    public LottoResult match(LottoLine candidate) {
        List<LottoLineResult> results =  lottoLines.stream()
                .map(line -> line.match(candidate))
                .toList();

        return LottoResult.of(id, results);
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
