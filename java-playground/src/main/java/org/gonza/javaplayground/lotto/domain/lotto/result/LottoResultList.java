package org.gonza.javaplayground.lotto.domain.lotto.result;

import java.util.List;

public class LottoResultList {
    private final String lottoId;
    private final List<LottoLineResult> lineResults;

    public LottoResultList(String lottoId, List<LottoLineResult> lineResults) {
        validate(lineResults);
        this.lottoId = lottoId;
        this.lineResults = lineResults;
    }

    private void validate(List<LottoLineResult> items) {
        if (items.isEmpty()) {
            throw new IllegalArgumentException("No items found");
        }
    }

    public String getLottoId() {
        return lottoId;
    }

    public List<LottoLineResult> getResults() {
        return lineResults;
    }
}
