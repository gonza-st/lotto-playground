package org.gonza.javaplayground.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.gonza.javaplayground.model.Lotto;
import org.gonza.javaplayground.model.LottoNumber;
import org.gonza.javaplayground.model.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class LottoStatisticsTest {
    private LottoVerifier 로또검증기;
    private LottoStatistics 로또통계;
    private List<LottoNumber> 당첨번호;
    private LottoNumber 보너스번호;

    @BeforeEach
    void setUp() {
        로또검증기 = new LottoVerifier();
        로또통계 = new LottoStatistics(로또검증기);

        당첨번호 = List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        );

        보너스번호 = new LottoNumber(7);

        로또검증기.당첨번호_설정하기(당첨번호);
        로또검증기.보너스번호_설정하기(보너스번호);
    }

    @Test
    @DisplayName("당첨 통계를 올바르게 계산한다")
    void 당첨_통계_계산() {
        List<Lotto> 구매_로또_목록 = new ArrayList<>();

        구매_로또_목록.add(new Lotto(List.of(
                new LottoNumber(10),
                new LottoNumber(11),
                new LottoNumber(12),
                new LottoNumber(13),
                new LottoNumber(14),
                new LottoNumber(15)
        )));

        구매_로또_목록.add(new Lotto(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(13),
                new LottoNumber(14),
                new LottoNumber(15)
        )));

        구매_로또_목록.add(new Lotto(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(14),
                new LottoNumber(15)
        )));

        구매_로또_목록.add(new Lotto(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(16),
                new LottoNumber(17)
        )));

        구매_로또_목록.add(new Lotto(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(15)
        )));

        구매_로또_목록.add(new Lotto(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(7)
        )));

        구매_로또_목록.add(new Lotto(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        )));

        Map<Rank, Integer> 당첨_통계 = 로또통계.당첨_통계_계산하기(구매_로또_목록);

        assertEquals(1, 당첨_통계.get(Rank.FIFTH));
        assertEquals(2, 당첨_통계.get(Rank.FOURTH));
        assertEquals(1, 당첨_통계.get(Rank.THIRD));
        assertEquals(1, 당첨_통계.get(Rank.SECOND));
        assertEquals(1, 당첨_통계.get(Rank.FIRST));

        assertFalse(당첨_통계.containsKey(Rank.MISS));
    }

    @Test
    @DisplayName("당첨된 로또가 없는 경우 모든 등수가 0으로 초기화된다")
    void 당첨_없는_케이스() {
        List<Lotto> 구매_로또_목록 = new ArrayList<>();

        구매_로또_목록.add(new Lotto(List.of(
                new LottoNumber(10),
                new LottoNumber(11),
                new LottoNumber(12),
                new LottoNumber(13),
                new LottoNumber(14),
                new LottoNumber(15)
        )));

        Map<Rank, Integer> 당첨_통계 = 로또통계.당첨_통계_계산하기(구매_로또_목록);

        assertEquals(0, 당첨_통계.get(Rank.FIFTH));
        assertEquals(0, 당첨_통계.get(Rank.FOURTH));
        assertEquals(0, 당첨_통계.get(Rank.THIRD));
        assertEquals(0, 당첨_통계.get(Rank.SECOND));
        assertEquals(0, 당첨_통계.get(Rank.FIRST));
    }
}