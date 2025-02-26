package org.gonza.javaplayground.lotto.domain.lotto.lotto;

import org.gonza.javaplayground.lotto.domain.lotto.result.LottoLineResult;
import org.gonza.javaplayground.lotto.domain.lotto.result.LottoResultList;

import java.util.List;
import java.util.UUID;

public class Lotto {
    private final String id;
    private final List<LottoLine> lottoLines;

    public Lotto(List<LottoLine> numbers) {
        this.id = UUID.randomUUID().toString();
        this.lottoLines = numbers;
    }

    public LottoResultList match(LottoLine candidate, LottoNumber bonus) {
        List<LottoLineResult> results = lottoLines.stream()
                .map(line -> line.match(candidate, bonus))
                .toList();

        return new LottoResultList(id, results);
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
                .map(this::lottoNumbersToNumbers)
                .toList();
    }

    private List<Integer> lottoNumbersToNumbers(List<LottoNumber> numbers) {
        return numbers.stream()
                .map(LottoNumber::value)
                .toList();
    }
}
