package org.gonza.javaplayground.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoNumberTest {

    @Test
    @DisplayName("유효 범위(1~45)의 숫자로 객체를 생성하면 정상적으로 생성된다.")
    void validNumberShouldCreateLottoNumber() {
        LottoNumber lottoNumber = new LottoNumber(10);
        assertNotNull(lottoNumber);
    }

    @Test
    @DisplayName("1보다 작은 숫자를 입력하면 예외가 발생한다.")
    void numberLessThanOneShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new LottoNumber(0);
        });
    }

    @Test
    @DisplayName("45보다 큰 숫자를 입력하면 예외가 발생한다.")
    void numberGreaterThanFortyFiveShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new LottoNumber(46);
        });
    }
}