package org.gonza.javaplayground.controller;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import org.gonza.javaplayground.model.Lotto;
import org.gonza.javaplayground.model.Rank;

public class LottoStatistics {
    private final LottoVerifier 로또검증기;

    public LottoStatistics(LottoVerifier 로또검증기) {
        this.로또검증기 = 로또검증기;
    }

    public Map<Rank, Integer> 당첨_통계_계산하기(List<Lotto> 구매_로또_목록) {
        Map<Rank, Integer> 당첨_통계 = 당첨_통계_초기화하기();

        for (Lotto 로또 : 구매_로또_목록) {
            int 일치_개수 = 로또검증기.일치_번호_개수_계산하기(로또);
            boolean 보너스_일치 = 로또검증기.보너스번호_일치_확인하기(로또);

            Rank 등수 = Rank.valueOf(일치_개수, 보너스_일치);

            if (등수 != Rank.MISS) {
                당첨_통계.put(등수, 당첨_통계.get(등수) + 1);
            }
        }

        return 당첨_통계;
    }

    private Map<Rank, Integer> 당첨_통계_초기화하기() {
        Map<Rank, Integer> 당첨_통계 = new EnumMap<>(Rank.class);

        for (Rank rank : Rank.values()) {
            if (rank != Rank.MISS) {
                당첨_통계.put(rank, 0);
            }
        }

        return 당첨_통계;
    }
}