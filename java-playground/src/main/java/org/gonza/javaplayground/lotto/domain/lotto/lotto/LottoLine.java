package org.gonza.javaplayground.lotto.domain.lotto.lotto;

import org.gonza.javaplayground.lotto.domain.lotto.result.LottoLineBonusResult;
import org.gonza.javaplayground.lotto.domain.lotto.result.LottoLineResult;

import java.util.*;

public class LottoLine {
    private List<LottoNumber> line;

    public LottoLine(List<LottoNumber> line) {
        this.line = line;
    }

    public List<LottoNumber> getAllNumbers() {
        return line;
    }

    public LottoLineResult match(LottoLine candidate, LottoNumber bonus) {

        Set<LottoNumber> input = new HashSet<>(candidate.line);
        Set<LottoNumber> resultSet = new HashSet<>(line);
        resultSet.retainAll(input);

        List<LottoNumber> result = new ArrayList<>(resultSet);

        Boolean isBonusMatched = !resultSet.contains(bonus) &&  new HashSet<>(line).contains(bonus);
        if (isBonusMatched) {
            return new LottoLineBonusResult(result);
        }

        return new LottoLineResult(result);
    }
}
