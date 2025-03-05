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

class CalculatorTest {
    private Calculator calculator;
    private List<LottoNumber> 당첨번호;
    private LottoNumber 보너스번호;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();

        당첨번호 = List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        );

        보너스번호 = new LottoNumber(7);

        calculator.당첨번호_설정하기(당첨번호);
        calculator.보너스번호_설정하기(보너스번호);
    }

    @Test
    @DisplayName("구매한 로또 번호와 당첨 번호를 비교하여 일치하는 번호의 개수를 반환한다")
    void 일치번호_개수_계산() {
        Lotto 구매로또 = new Lotto(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(8),
                new LottoNumber(9),
                new LottoNumber(10)
        ));

        int 일치개수 = calculator.일치_번호_개수_계산하기(구매로또);

        assertEquals(3, 일치개수);
    }

    @Test
    @DisplayName("당첨 통계를 올바르게 계산한다")
    void 당첨_통계_계산() {
        List<Lotto> 구매_로또_목록 = new ArrayList<>();


        구매_로또_목록.add(new Lotto(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(8),
                new LottoNumber(9),
                new LottoNumber(10)
        )));


        구매_로또_목록.add(new Lotto(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(9),
                new LottoNumber(10)
        )));


        구매_로또_목록.add(new Lotto(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(10)
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

        Map<Rank, Integer> 당첨_통계 = calculator.당첨_통계_계산하기(구매_로또_목록);

        assertEquals(1, 당첨_통계.get(Rank.FIFTH));
        assertEquals(1, 당첨_통계.get(Rank.FOURTH));
        assertEquals(1, 당첨_통계.get(Rank.THIRD));
        assertEquals(1, 당첨_통계.get(Rank.SECOND));
        assertEquals(1, 당첨_통계.get(Rank.FIRST));
    }

    @Test
    @DisplayName("수익률을 올바르게 계산한다")
    void 수익률_계산() {
        Map<Rank, Integer> 당첨_통계 = Map.of(
                Rank.FIFTH, 1,
                Rank.FOURTH, 0,
                Rank.THIRD, 0,
                Rank.SECOND, 0,
                Rank.FIRST, 0
        );

        int 투자_금액 = 5000;
        double 수익률 = calculator.수익률_계산하기(당첨_통계, 투자_금액);

        assertEquals(1.0, 수익률, 0.001);
    }
}