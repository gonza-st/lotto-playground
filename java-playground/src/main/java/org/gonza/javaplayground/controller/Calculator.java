package org.gonza.javaplayground.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.gonza.javaplayground.model.Lotto;
import org.gonza.javaplayground.model.LottoNumber;

public class Calculator {
    private static final Map<Integer, Integer> 당첨_목록 = new HashMap<>() {{
        put(3, 5_000);
        put(4, 50_000);
        put(5, 1_500_000);
        put(6, 2_000_000_000);
    }};

    private Lotto 당첨번호;

    public void 당첨번호_설정하기(LottoNumber[] 당첨번호_배열) {
        this.당첨번호 = 당첨로또_생성하기(당첨번호_배열);
    }

    private Lotto 당첨로또_생성하기(LottoNumber[] 당첨번호_배열) {
        Lotto 당첨_로또 = new Lotto();
        for (LottoNumber 번호 : 당첨번호_배열) {
            당첨_로또.번호_추가하기(번호);
        }
        return 당첨_로또;
    }

    public Map<Integer, Integer> 당첨_통계_계산하기(ArrayList<Lotto> 구매_로또_목록) {
        Map<Integer, Integer> 당첨_통계 = 당첨_통계_초기화하기();

        for (Lotto 로또 : 구매_로또_목록) {
            int 일치_개수 = 일치_번호_개수_계산하기(로또);
            if (일치_개수 >= 3) {
                당첨_통계.put(일치_개수, 당첨_통계.get(일치_개수) + 1);
            }
        }

        return 당첨_통계;
    }

    private Map<Integer, Integer> 당첨_통계_초기화하기() {
        return new HashMap<>() {{
            put(3, 0);
            put(4, 0);
            put(5, 0);
            put(6, 0);
        }};
    }

    public int 일치_번호_개수_계산하기(Lotto 로또) {
        int 일치_개수 = 0;
        for (LottoNumber 번호 : 로또.번호_목록_가져오기()) {
            if (당첨번호_포함_여부_확인하기(번호)) {
                일치_개수++;
            }
        }
        return 일치_개수;
    }

    private boolean 당첨번호_포함_여부_확인하기(LottoNumber 번호) {
        return 당첨번호.번호_목록_가져오기().stream()
                .anyMatch(당첨번호 -> 당첨번호.번호_값_가져오기() == 번호.번호_값_가져오기());
    }

    public double 수익률_계산하기(Map<Integer, Integer> 당첨_통계, int 투자_금액) {
        int 총_당첨금 = 총_당첨금_계산하기(당첨_통계);
        return (double) 총_당첨금 / 투자_금액;
    }

    private int 총_당첨금_계산하기(Map<Integer, Integer> 당첨_통계) {
        return 당첨_통계.entrySet().stream()
                .mapToInt(entry -> 당첨_목록.get(entry.getKey()) * entry.getValue())
                .sum();
    }
}