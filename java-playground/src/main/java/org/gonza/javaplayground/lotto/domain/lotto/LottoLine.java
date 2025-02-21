package org.gonza.javaplayground.lotto.domain.lotto;

import org.gonza.javaplayground.lotto.domain.lotto.result.LottoLineResult;

import java.util.*;

public class LottoLine {
    private List<Integer> line;

    public LottoLine(List<Integer> line) {
        this.line = line;
    }

    public List<Integer> getAllNumbers() {
        return line;
    }

    public LottoLineResult match(LottoLine candidate) {

        Set<Integer> input = new HashSet<>(candidate.line);
        Set<Integer> resultSet = new HashSet<>(line);
        resultSet.retainAll(input);

        List<Integer> result = new ArrayList<>(resultSet);

        return new LottoLineResult(result);
    }
}
