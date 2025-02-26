package org.gonza.javaplayground.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.gonza.javaplayground.model.Lotto;

public class LottoStatistics {
    private final LottoVerifier 로또검증기;

    public LottoStatistics(LottoVerifier 로또검증기) {
        this.로또검증기 = 로또검증기;
    }

    public Map<Integer, Integer> 당첨_통계_계산하기(List<Lotto> 구매_로또_목록) {
        Map<Integer, Integer> 당첨_통계 = 당첨_통계_초기화하기();

        for (Lotto 로또 : 구매_로또_목록) {
            int 일치_개수 = 로또검증기.일치_번호_개수_계산하기(로또);
            if (일치_개수 >= 3) {
                당첨_통계.put(일치_개수, 당첨_통계.get(일치_개수) + 1);
            }
        }

        return 당첨_통계;
    }

    private Map<Integer, Integer> 당첨_통계_초기화하기() {
        Map<Integer, Integer> 당첨_통계 = new HashMap<>();
        당첨_통계.put(3, 0);
        당첨_통계.put(4, 0);
        당첨_통계.put(5, 0);
        당첨_통계.put(6, 0);
        return 당첨_통계;
    }
}