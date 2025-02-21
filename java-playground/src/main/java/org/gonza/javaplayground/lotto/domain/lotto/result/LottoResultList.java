package org.gonza.javaplayground.lotto.domain.lotto.result;

import java.util.List;

public class LottoResultList {
    private final String lottoId;
    private final List<LottoLineResult> lineResults;

    private LottoResultList(String lottoId, List<LottoLineResult> lineResults) {
        this.lottoId = lottoId;
        this.lineResults = lineResults;
    }

    public String getLottoId() {
        return lottoId;
    }

    public List<LottoLineResult> getResults() {
        return lineResults;
    }

    public static LottoResultList of(String lottoId, List<LottoLineResult> lineResult) {
        validate(lineResult);
        return new LottoResultList(lottoId, lineResult);
    }

    private static void validate(List<LottoLineResult> items) {
        if (items.isEmpty()) {
            throw new IllegalArgumentException("No items found");
        }
    }
}
