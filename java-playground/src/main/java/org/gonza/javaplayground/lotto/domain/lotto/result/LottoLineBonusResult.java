package org.gonza.javaplayground.lotto.domain.lotto.result;

import org.gonza.javaplayground.lotto.domain.lotto.lotto.LottoNumber;

import java.util.List;

public class LottoLineBonusResult extends LottoLineResult {
    private static final Integer BONUS_FLAG = -1;

    public LottoLineBonusResult(List<LottoNumber> results) {
        super(results);
    }

    @Override
    public Integer getMatchedCount() {
        return BONUS_FLAG;
    }
}
