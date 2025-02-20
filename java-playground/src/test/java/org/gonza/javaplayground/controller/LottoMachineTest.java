package org.gonza.javaplayground.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.gonza.javaplayground.model.Lotto;
import org.gonza.javaplayground.model.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class LottoMachineTest {
    private LottoMachine lottoMachine;
    private static final int 로또_한장_가격 = 1000;

    @BeforeEach
    void setUp() {
        lottoMachine = new LottoMachine();
    }

    @Test
    @DisplayName("로또는 1000원으로 구매할 수 있다")
    void 최소_구매_금액_테스트() {
        Money 구매금액 = new Money(로또_한장_가격);

        ArrayList<Lotto> 구매한_로또_목록 = lottoMachine.로또_구매하기(구매금액);

        assertEquals(1, 구매한_로또_목록.size());
    }

    @ParameterizedTest
    @ValueSource(ints = {500, 999})
    @DisplayName("1000원 미만으로는 로또를 구매할 수 없다")
    void 최소_구매_금액_미만_테스트(int 금액) {
        Money 구매금액 = new Money(금액);

        assertThrows(IllegalArgumentException.class,
                () -> lottoMachine.로또_구매하기(구매금액));
    }

    @Test
    @DisplayName("로또 구매 금액은 1000원 단위여야 한다")
    void 구매_단위_테스트() {
        Money 구매금액 = new Money(1500);

        assertThrows(IllegalArgumentException.class,
                () -> lottoMachine.로또_구매하기(구매금액));
    }

    @Test
    @DisplayName("구매 금액에 해당하는 만큼 로또를 발급해야 한다")
    void 로또_수량_테스트() {
        Money 구매금액 = new Money(5000);

        ArrayList<Lotto> 구매한_로또_목록 = lottoMachine.로또_구매하기(구매금액);

        assertEquals(5, 구매한_로또_목록.size());
    }

    @Test
    @DisplayName("로또 한 장은 6개의 번호를 가져야 한다")
    void 로또_번호_개수_테스트() {
        Money 구매금액 = new Money(로또_한장_가격);

        ArrayList<Lotto> 구매한_로또_목록 = lottoMachine.로또_구매하기(구매금액);
        Lotto 로또 = 구매한_로또_목록.getFirst();

        assertEquals(6, 로또.번호_목록_가져오기().size());
    }

    @Test
    @DisplayName("로또 번호는 1부터 45 사이의 수여야 한다")
    void 로또_번호_범위_테스트() {
        Money 구매금액 = new Money(로또_한장_가격);

        ArrayList<Lotto> 구매한_로또_목록 = lottoMachine.로또_구매하기(구매금액);
        Lotto 로또 = 구매한_로또_목록.getFirst();

        로또.번호_목록_가져오기().forEach(번호 -> {
            assertTrue(번호.번호_값_가져오기() >= 1 && 번호.번호_값_가져오기() <= 45);
        });
    }

    @Test
    @DisplayName("로또 번호는 중복되지 않아야 한다")
    void 로또_번호_중복_테스트() {
        Money 구매금액 = new Money(로또_한장_가격);

        ArrayList<Lotto> 구매한_로또_목록 = lottoMachine.로또_구매하기(구매금액);
        Lotto 로또 = 구매한_로또_목록.getFirst();

        Set<Integer> 중복검사_세트 = new HashSet<>();
        로또.번호_목록_가져오기().forEach(번호 -> {
            assertTrue(중복검사_세트.add(번호.번호_값_가져오기()));
        });
        assertEquals(6, 중복검사_세트.size());
    }
}