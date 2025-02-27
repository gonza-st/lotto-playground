package org.gonza.javaplayground.controller;

import java.util.List;
import java.util.Map;
import org.gonza.javaplayground.model.Lotto;
import org.gonza.javaplayground.model.LottoNumber;

public class Calculator {
    private final LottoVerifier 로또검증기;
    private final LottoStatistics 로또통계;
    private final PrizeCalculator 상금계산기;

    public Calculator() {
        this.로또검증기 = new LottoVerifier();
        this.로또통계 = new LottoStatistics(로또검증기);
        this.상금계산기 = new PrizeCalculator();
    }

    public void 당첨번호_설정하기(List<LottoNumber> 당첨번호_배열) {
        로또검증기.당첨번호_설정하기(당첨번호_배열);
    }

    public int 일치_번호_개수_계산하기(Lotto 로또) {
        return 로또검증기.일치_번호_개수_계산하기(로또);
    }

    public Map<Integer, Integer> 당첨_통계_계산하기(List<Lotto> 구매_로또_목록) {
        return 로또통계.당첨_통계_계산하기(구매_로또_목록);
    }

    public double 수익률_계산하기(Map<Integer, Integer> 당첨_통계, int 투자_금액) {
        return 상금계산기.수익률_계산하기(당첨_통계, 투자_금액);
    }

    public Map<Integer, Integer> 당첨금액_목록_가져오기() {
        return 상금계산기.당첨금액_목록_가져오기();
    }
}