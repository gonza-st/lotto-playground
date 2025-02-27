package org.gonza.javaplayground.lotto.domain.lotto.result;

import org.gonza.javaplayground.lotto.domain.lotto.lotto.LottoNumber;

import java.util.List;
import java.util.Objects;

public class LottoLineResult {
    private final List<LottoNumber> result;

    public LottoLineResult(List<LottoNumber> results) {
        this.result = results;
    }

    public Integer getMatchedCount() {
        return result.size();
    }

    public List<Integer> getResult() {
        return result.stream().map(LottoNumber::value).toList();
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
