package org.gonza.javaplayground.lotto.domain.lotto.lotto;

import org.gonza.javaplayground.lotto.domain.lotto.result.LottoLineResult;

import java.util.*;

public class LottoLine {
    private List<LottoNumber> line;

    public LottoLine(List<LottoNumber> line) {
        this.line = line;
    }

    public List<Integer> getAllNumbers() {
        return line.stream()
                .map(LottoNumber::value)
                .toList();
    }

    public LottoLineResult match(LottoLine candidate) {

        Set<LottoNumber> input = new HashSet<>(candidate.line);
        Set<LottoNumber> resultSet = new HashSet<>(line);
        resultSet.retainAll(input);

        List<LottoNumber> result = new ArrayList<>(resultSet);

        List<Integer> list = result.stream()
                .map(LottoNumber::value)
                .toList();

        return new LottoLineResult(list);
    }
}
