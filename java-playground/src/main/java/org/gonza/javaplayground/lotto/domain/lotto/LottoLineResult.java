package org.gonza.javaplayground.lotto.domain.lotto;

import java.util.List;

public class LottoLineResult {
    private final List<Integer> result;

    public LottoLineResult(List<Integer> results) {
        this.result = results;
    }

    public Integer getMatchedCount() {
        return result.size();
    }

    public List<Integer> getResult() {
        return result;
    }
}
