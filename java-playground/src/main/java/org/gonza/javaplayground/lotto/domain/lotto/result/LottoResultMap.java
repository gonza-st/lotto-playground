package org.gonza.javaplayground.lotto.domain.lotto.result;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class LottoResultMap {
    private final String lottoId;
    private final Map<Integer, List<LottoLineResult>> lineResultMap;

    public LottoResultMap(String lottoId, Map<Integer, List<LottoLineResult>> lineResultMap) {
        validation(lottoId, lineResultMap);

        this.lottoId = lottoId;
        this.lineResultMap = lineResultMap;
    }

    private void validation(String lottoId, Map<Integer, List<LottoLineResult>> lineResultMap) {
        if (Objects.isNull(lottoId)) {
            throw new IllegalArgumentException("lottoId cannot be null");
        }

        if (lineResultMap.isEmpty()) {
            throw new IllegalArgumentException("The map is empty");
        }
    }

    public String getLottoId() {
        return lottoId;
    }

    public Map<Integer, List<LottoLineResult>> getLineResultMap() {
        return lineResultMap;
    }

    public static LottoResultMap from(LottoResultList lottoResultList) {
        Map<Integer, List<LottoLineResult>> map = lottoResultList.getResults().stream()
                .collect(Collectors.groupingBy(LottoLineResult::getMatchedCount));

        return new LottoResultMap(lottoResultList.getLottoId(), map);
    }
}
