package org.gonza.javaplayground.lotto.domain.lotto;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LottoLineResult that = (LottoLineResult) o;
        return Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(result);
    }
}
