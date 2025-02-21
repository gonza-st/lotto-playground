package org.gonza.javaplayground.lotto.domain.lotto.result;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record LottoResultMap(
        String lottoId,
        Map<Integer, List<LottoLineResult>> lineResultMap
) {

    public static LottoResultMap from(LottoResult lottoResult) {
        Map<Integer, List<LottoLineResult>> map = lottoResult.getResults().stream()
                .collect(Collectors.groupingBy(LottoLineResult::getMatchedCount));

        return new LottoResultMap(lottoResult.getLottoId(), map);
    }
}
