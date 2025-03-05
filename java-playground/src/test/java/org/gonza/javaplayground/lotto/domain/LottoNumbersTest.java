package org.gonza.javaplayground.lotto.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class LottoNumbersTest {

    @Test
    void LottoNumber는_1_이상이_아니면_예외가_발생한다() {
        assertThrows(IllegalArgumentException.class,
                () -> LottoNumbers.of(List.of(0, 2, 3, 4, 5, 6)));
        assertDoesNotThrow(
                () -> LottoNumbers.of(List.of(1, 2, 3, 4, 5, 6)));
    }

    @Test
    void LottoNumber는_45_이하가_아니면_예외가_발생한다() {
        assertThrows(IllegalArgumentException.class,
                () -> LottoNumbers.of(List.of(1, 2, 3, 4, 5, 46)));
        assertDoesNotThrow(
                () -> LottoNumbers.of(List.of(1, 2, 3, 4, 5, 45)));
    }

    @Test
    void LottoNumber는_6_자리가_아니면_예외가_발생한다() {
        assertThrows(IllegalArgumentException.class,
                () -> LottoNumbers.of(List.of(1, 2, 3, 4, 5)));
        assertThrows(IllegalArgumentException.class,
                () -> LottoNumbers.of(List.of(1, 2, 3, 4, 5, 6, 7)));
        assertDoesNotThrow(
                () -> LottoNumbers.of(List.of(1, 2, 3, 4, 5, 6)));
    }

    @Test
    void LottoNumber는_자동일_수_있다() {
        LottoNumbers numbers = LottoNumbers.of(List.of(1, 2, 3, 4, 5, 6));

        assertTrue(numbers.isAutomatic());
    }

    @Test
    void LottoNumber는_수동일_수_있다() {
        LottoNumbers numbers = LottoNumbers.manualOf(List.of(1, 2, 3, 4, 5, 6));

        assertTrue(numbers.isManual());
    }
}