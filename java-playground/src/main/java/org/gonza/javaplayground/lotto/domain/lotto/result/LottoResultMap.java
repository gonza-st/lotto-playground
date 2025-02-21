package org.gonza.javaplayground.lotto.domain.lotto.result;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record LottoResultMap(
        String lottoId,
        Map<Integer, List<LottoLineResult>> lineResultMap
) {

    public static LottoResultMap from(LottoResultList lottoResultList) {
        Map<Integer, List<LottoLineResult>> map = lottoResultList.getResults().stream()
                .collect(Collectors.groupingBy(LottoLineResult::getMatchedCount));

        return new LottoResultMap(lottoResultList.getLottoId(), map);
    }
}
