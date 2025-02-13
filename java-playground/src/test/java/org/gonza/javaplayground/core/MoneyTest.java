package org.gonza.javaplayground.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {

    @Test
    @DisplayName("금액을 생성할 수 있다.")
    void createMoneySuccessTest() throws Exception {

        Money money = new Money(new BigDecimal(1000));

        assertThat(money.getValue()).isEqualTo(BigDecimal.valueOf(1000));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -1000})
    @DisplayName("금액은 0원 이상이어야한다.")
    void createMoneyFailTest_underZero(int value) throws Exception {

        assertThatThrownBy(() -> new Money(new BigDecimal(value)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("금액은 0보다 작을 수 없습니다.");
    }
}
