package org.gonza.javaplayground.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTest {

    @Test
    @DisplayName("금액을 생성할 수 있다")
    void 금액_생성_테스트() {
        int 금액 = 1000;

        Money money = new Money(금액);

        assertEquals(금액, money.금액_가져오기());
    }

}