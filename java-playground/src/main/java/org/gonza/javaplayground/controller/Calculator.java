package org.gonza.javaplayground.controller;

import java.util.List;
import java.util.Map;
import org.gonza.javaplayground.model.Lotto;
import org.gonza.javaplayground.model.LottoNumber;
import org.gonza.javaplayground.model.Rank;

public class Calculator {
    private final LottoVerifier 로또검증기;
    private final LottoStatistics 로또통계;

    public Calculator() {
        this.로또검증기 = new LottoVerifier();
        this.로또통계 = new LottoStatistics(로또검증기);
    }

    public void 당첨번호_설정하기(List<LottoNumber> 당첨번호_배열) {
        로또검증기.당첨번호_설정하기(당첨번호_배열);
    }

    public void 보너스번호_설정하기(LottoNumber 보너스번호) {
        로또검증기.보너스번호_설정하기(보너스번호);
    }

    public int 일치_번호_개수_계산하기(Lotto 로또) {
        return 로또검증기.일치_번호_개수_계산하기(로또);
    }

    public Map<Rank, Integer> 당첨_통계_계산하기(List<Lotto> 구매_로또_목록) {
        return 로또통계.당첨_통계_계산하기(구매_로또_목록);
    }

    public double 수익률_계산하기(Map<Rank, Integer> 당첨_통계, int 투자_금액) {
        int 총_당첨금 = 당첨_통계.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getWinningMoney() * entry.getValue())
                .sum();
        return (double) 총_당첨금 / 투자_금액;
    }
}