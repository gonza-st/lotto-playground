package org.gonza.javaplayground.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomGeneratorTest {
    private NumberGenerator 난수생성기;

    @BeforeEach
    void setUp() {
        난수생성기 = new RandomGeneratorDetail();
    }

    @RepeatedTest(100)
    @DisplayName("생성된 난수는 주어진 범위 내의 값이어야 한다")
    void 범위_내_난수_생성() {
        int 시작 = 1;
        int 끝 = 45;

        int 생성된_난수 = 난수생성기.범위_내_난수_생성하기(시작, 끝);

        assertTrue(생성된_난수 >= 시작);
        assertTrue(생성된_난수 <= 끝);
    }
}