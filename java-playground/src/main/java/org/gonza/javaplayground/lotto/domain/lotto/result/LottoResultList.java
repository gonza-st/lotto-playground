package org.gonza.javaplayground.lotto.domain.lotto.result;

import java.util.List;
import java.util.Objects;

public class LottoResultList {
    private final String lottoId;
    private final List<LottoLineResult> lineResults;

    public LottoResultList(String lottoId, List<LottoLineResult> lineResults) {
        validate(lottoId, lineResults);

        this.lottoId = lottoId;
        this.lineResults = lineResults;
    }

    private void validate(String lottoId, List<LottoLineResult> items) {
        if (Objects.isNull(lottoId)) {
            throw new IllegalArgumentException("lottoId cannot be null");
        }

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
