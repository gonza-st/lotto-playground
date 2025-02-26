package org.gonza.javaplayground.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.gonza.javaplayground.model.Lotto;
import org.gonza.javaplayground.model.LottoNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    @DisplayName("구매한 로또 번호와 당첨 번호를 비교하여 일치하는 번호의 개수를 반환한다")
    void 일치번호_개수_계산() {
        List<LottoNumber> 당첨번호 = List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        );
        calculator.당첨번호_설정하기(당첨번호);

        Lotto 구매로또 = new Lotto(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(7),
                new LottoNumber(8),
                new LottoNumber(9)
        ));

        int 일치개수 = calculator.일치_번호_개수_계산하기(구매로또);

        assertEquals(3, 일치개수);
    }


    @Test
    @DisplayName("당첨 통계는 3개 이상 일치하는 경우만 포함한다")
    void 당첨_통계_최소_일치개수() {
        List<LottoNumber> 당첨번호 = List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        );
        calculator.당첨번호_설정하기(당첨번호);

        ArrayList<Lotto> 구매_로또_목록 = new ArrayList<>();
        구매_로또_목록.add(로또_생성하기(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(7),
                new LottoNumber(8),
                new LottoNumber(9)
        )));
        구매_로또_목록.add(로또_생성하기(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        )));

        Map<Integer, Integer> 당첨_통계 = calculator.당첨_통계_계산하기(구매_로또_목록);

        assertEquals(1, 당첨_통계.get(3));
        assertEquals(0, 당첨_통계.get(4));
        assertEquals(0, 당첨_통계.get(5));
        assertEquals(1, 당첨_통계.get(6));
    }

    private Lotto 로또_생성하기(List<LottoNumber> lottoNumber) {
        Lotto 로또 = new Lotto(lottoNumber);
        return 로또;
    }
}