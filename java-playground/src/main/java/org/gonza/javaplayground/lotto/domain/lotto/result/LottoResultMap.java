package org.gonza.javaplayground.lotto.domain.lotto.result;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public record LottoResultMap(
        String lottoId,
        Map<Integer, List<LottoLineResult>> result
) implements LottoResult<Map<Integer, List<LottoLineResult>>> {
    public LottoResultMap {
        validation(lottoId, result);
    }

    private void validation(String lottoId, Map<Integer, List<LottoLineResult>> result) {
        if (Objects.isNull(lottoId)) {
            throw new IllegalArgumentException("lottoId cannot be null");
        }

        if (result.isEmpty()) {
            throw new IllegalArgumentException("The map is empty");
        }
    }

    public static LottoResultMap from(LottoResultList lottoResultList) {
        Map<Integer, List<LottoLineResult>> map = lottoResultList.result().stream()
                .collect(Collectors.groupingBy(LottoLineResult::getMatchedCount));

        return new LottoResultMap(lottoResultList.lottoId(), map);
    }
}
