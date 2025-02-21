package org.gonza.javaplayground.lotto.domain.lotto.result;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoResultMap {
    private final String lottoId;
    private final Map<Integer, List<LottoLineResult>> lineResultMap;

    private LottoResultMap(String lottoId, Map<Integer, List<LottoLineResult>> lineResultMap) {
        this.lottoId = lottoId;
        this.lineResultMap = lineResultMap;
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
