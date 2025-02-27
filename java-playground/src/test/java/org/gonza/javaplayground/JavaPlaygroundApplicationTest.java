package org.gonza.javaplayground;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import org.gonza.javaplayground.controller.Calculator;
import org.gonza.javaplayground.controller.LottoMachine;
import org.gonza.javaplayground.controller.LottoPurchaseService;
import org.gonza.javaplayground.model.Lotto;
import org.gonza.javaplayground.model.LottoNumber;
import org.gonza.javaplayground.model.Money;
import org.gonza.javaplayground.model.Rank;
import org.gonza.javaplayground.view.io.OutputManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JavaPlaygroundApplicationTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private OutputManager 프린터;
    private LottoMachine 로또기계;
    private LottoPurchaseService 로또구매서비스;
    private Calculator 계산기;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
        프린터 = new OutputManager();
        로또기계 = new LottoMachine();
        로또구매서비스 = new LottoPurchaseService(로또기계);
        계산기 = new Calculator();
    }

    @Test
    @DisplayName("전체 어플리케이션 통합 테스트 - 로또 구매 및 당첨 확인")
    void 전체_어플리케이션_통합_테스트() {
        Money 구매_금액 = new Money(8000);
        List<Lotto> 구매_로또_목록 = 로또구매서비스.로또_구매하기(구매_금액);

        List<LottoNumber> 당첨_번호 = List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        );
        계산기.당첨번호_설정하기(당첨_번호);

        LottoNumber 보너스_번호 = new LottoNumber(7);
        계산기.보너스번호_설정하기(보너스_번호);

        Map<Rank, Integer> 당첨_통계 = 계산기.당첨_통계_계산하기(구매_로또_목록);
        double 수익률 = 계산기.수익률_계산하기(당첨_통계, 구매_금액.금액_가져오기());

        assertEquals(8, 구매_로또_목록.size());
        assertNotNull(당첨_통계);
        assertTrue(수익률 >= 0.0);

        프린터.당첨_통계_출력하기(당첨_통계, 수익률);
        String 출력_결과 = outputStream.toString();
        assertTrue(출력_결과.contains("당첨 통계"));
        assertTrue(출력_결과.contains("3개 일치"));
        assertTrue(출력_결과.contains("4개 일치"));
        assertTrue(출력_결과.contains("5개 일치"));
        assertTrue(출력_결과.contains("6개 일치"));
        assertTrue(출력_결과.contains("총 수익률"));
    }

    @Test
    @DisplayName("구매 금액에 따른 로또 수량 검증")
    void 로또_구매_수량_검증() {
        Money 금액_1000원 = new Money(1000);
        List<Lotto> 구매_로또_1장 = 로또구매서비스.로또_구매하기(금액_1000원);
        assertEquals(1, 구매_로또_1장.size());

        Money 금액_5000원 = new Money(5000);
        List<Lotto> 구매_로또_5장 = 로또구매서비스.로또_구매하기(금액_5000원);
        assertEquals(5, 구매_로또_5장.size());
    }
}