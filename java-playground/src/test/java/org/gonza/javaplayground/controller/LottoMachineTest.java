package org.gonza.javaplayground.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.gonza.javaplayground.model.Lotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LottoMachineTest {
    private LottoMachine 로또머신;

    @BeforeEach
    void setUp() {
        로또머신 = new LottoMachine();
    }

    @Test
    @DisplayName("로또 한 장은 6개의 번호를 가져야 한다")
    void 로또_번호_개수_테스트() {
        List<Lotto> 구매한_로또_목록 = 로또머신.로또_생성하기(1);
        Lotto 로또 = 구매한_로또_목록.getFirst();

        assertEquals(6, 로또.로또번호_목록_가져오기().size());
    }

    @Test
    @DisplayName("로또 번호는 중복되지 않아야 한다")
    void 로또_번호_중복_테스트() {
        List<Lotto> 구매한_로또_목록 = 로또머신.로또_생성하기(1);
        Lotto 로또 = 구매한_로또_목록.getFirst();

        Set<Integer> 중복검사_세트 = new HashSet<>();
        로또.로또번호_목록_가져오기().forEach(번호 -> {
            assertTrue(중복검사_세트.add(번호.번호_값_가져오기()));
        });
        assertEquals(6, 중복검사_세트.size());
    }

    @Test
    @DisplayName("요청한 수량만큼 로또를 생성해야 한다")
    void 로또_생성_수량_테스트() {
        int 요청수량 = 5;

        List<Lotto> 생성된_로또_목록 = 로또머신.로또_생성하기(요청수량);

        assertEquals(요청수량, 생성된_로또_목록.size());
    }
}