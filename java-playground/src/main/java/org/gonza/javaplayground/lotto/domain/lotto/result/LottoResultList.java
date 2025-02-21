package org.gonza.javaplayground.lotto.domain.lotto.result;

import java.util.List;
import java.util.Objects;

public record LottoResultList(
        String lottoId,
        List<LottoLineResult> result
) implements LottoResult<List<LottoLineResult>> {
    public LottoResultList {
        validate(lottoId, result);
    }

    private void validate(String lottoId, List<LottoLineResult> results) {
        if (Objects.isNull(lottoId)) {
            throw new IllegalArgumentException("lottoId cannot be null");
        }

        if (results.isEmpty()) {
            throw new IllegalArgumentException("No items found");
        }
    }
}
