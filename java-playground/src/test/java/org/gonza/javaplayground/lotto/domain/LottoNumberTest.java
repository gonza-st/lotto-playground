package org.gonza.javaplayground.lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoNumberTest {

    @Test
    void LottoNumber는_1_이상이_아니면_예외가_발생한다() {
        assertThrows(IllegalArgumentException.class,
                () -> LottoNumber.of(List.of(0, 2, 3, 4, 5, 6)));
        assertDoesNotThrow(
                () -> LottoNumber.of(List.of(1, 2, 3, 4, 5, 6)));
    }

    @Test
    void LottoNumber는_45_이하가_아니면_예외가_발생한다() {
        assertThrows(IllegalArgumentException.class,
                () -> LottoNumber.of(List.of(1, 2, 3, 4, 5, 46)));
        assertDoesNotThrow(
                () -> LottoNumber.of(List.of(1, 2, 3, 4, 5, 45)));
    }

    @Test
    void LottoNumber는_6_자리가_아니면_예외가_발생한다() {
        assertThrows(IllegalArgumentException.class,
                () -> LottoNumber.of(List.of(1, 2, 3, 4, 5)));
        assertThrows(IllegalArgumentException.class,
                () -> LottoNumber.of(List.of(1, 2, 3, 4, 5, 6, 7)));
        assertDoesNotThrow(
                () -> LottoNumber.of(List.of(1, 2, 3, 4, 5, 6)));
    }
}