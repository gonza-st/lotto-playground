package org.gonza.javaplayground.controller;

import java.util.List;
import org.gonza.javaplayground.model.Lotto;
import org.gonza.javaplayground.model.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoPurchaseServiceTest {
    private LottoPurchaseService 로또구매서비스;
    private static final int 로또_한장_가격 = 1000;

    @BeforeEach
    void setUp() {
        LottoMachine 로또머신 = new LottoMachine();
        로또구매서비스 = new LottoPurchaseService(로또머신);
    }

    @Test
    @DisplayName("로또는 1000원으로 구매할 수 있다")
    void 최소_구매_금액_테스트() {
        Money 구매금액 = new Money(로또_한장_가격);

        List<Lotto> 구매한_로또_목록 = 로또구매서비스.로또_구매하기(구매금액);

        assertEquals(1, 구매한_로또_목록.size());
    }

    @ParameterizedTest
    @ValueSource(ints = {500, 999})
    @DisplayName("1000원 미만으로는 로또를 구매할 수 없다")
    void 최소_구매_금액_미만_테스트(int 금액) {
        Money 구매금액 = new Money(금액);

        assertThrows(IllegalArgumentException.class,
                () -> 로또구매서비스.로또_구매하기(구매금액));
    }

    @Test
    @DisplayName("로또 구매 금액은 1000원 단위여야 한다")
    void 구매_단위_테스트() {
        Money 구매금액 = new Money(1500);

        assertThrows(IllegalArgumentException.class,
                () -> 로또구매서비스.로또_구매하기(구매금액));
    }

    @Test
    @DisplayName("구매 금액에 해당하는 만큼 로또를 발급해야 한다")
    void 로또_수량_테스트() {
        Money 구매금액 = new Money(5000);

        List<Lotto> 구매한_로또_목록 = 로또구매서비스.로또_구매하기(구매금액);

        assertEquals(5, 구매한_로또_목록.size());
    }
}