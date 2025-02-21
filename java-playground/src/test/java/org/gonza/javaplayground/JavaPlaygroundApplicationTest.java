package org.gonza.javaplayground;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;
import org.gonza.javaplayground.controller.Calculator;
import org.gonza.javaplayground.controller.LottoMachine;
import org.gonza.javaplayground.model.Lotto;
import org.gonza.javaplayground.model.LottoNumber;
import org.gonza.javaplayground.model.Money;
import org.gonza.javaplayground.view.Printer;
import org.gonza.javaplayground.view.io.InputManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JavaPlaygroundApplicationTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    @DisplayName("전체 어플리케이션 통합 테스트")
    void 전체_어플리케이션_통합_테스트() {
        Printer printer = new Printer();
        InputManager inputManager = new InputManager();
        LottoMachine lottoMachine = new LottoMachine();
        Calculator calculator = new Calculator();

        Money money = new Money(8000);
        ArrayList<Lotto> lottos = lottoMachine.로또_구매하기(money);

        LottoNumber[] winningNumbers = new LottoNumber[]{
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        };

        calculator.당첨번호_설정하기(winningNumbers);
        Map<Integer, Integer> statistics = calculator.당첨_통계_계산하기(lottos);
        double returnRate = calculator.수익률_계산하기(statistics, money.금액_가져오기());

        assertTrue(returnRate >= 0.0);
        assertEquals(8, lottos.size());
        assertNotNull(statistics);
    }
}