package org.gonza.javaplayground.controller;

import java.util.HashMap;
import java.util.Map;

public class PrizeCalculator {
    private static final Map<Integer, Integer> 당첨_목록 = new HashMap<>() {{
        put(3, 5_000);
        put(4, 50_000);
        put(5, 1_500_000);
        put(6, 2_000_000_000);
    }};

    public double 수익률_계산하기(Map<Integer, Integer> 당첨_통계, int 투자_금액) {
        int 총_당첨금 = 총_당첨금_계산하기(당첨_통계);
        return (double) 총_당첨금 / 투자_금액;
    }

    private int 총_당첨금_계산하기(Map<Integer, Integer> 당첨_통계) {
        return 당첨_통계.entrySet().stream()
                .mapToInt(entry -> 당첨_목록.get(entry.getKey()) * entry.getValue())
                .sum();
    }

    public Map<Integer, Integer> 당첨금액_목록_가져오기() {
        return new HashMap<>(당첨_목록);
    }
}