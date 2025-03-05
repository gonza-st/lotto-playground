package org.gonza.javaplayground.lotto.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.gonza.javaplayground.lotto.domain.LottoNumber;
import org.gonza.javaplayground.lotto.domain.LottoNumbers;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class RandomLottoNumbersTest {

    @Test
    void LottoNumber는_6자리이다() {
        LottoNumbers number = RandomLottoNumbers.generate();

        assertEquals(6, number.size());
    }

    @Test
    void LottoNumber는_1에서_45사이_숫자로_구성된다() {
        LottoNumbers number = RandomLottoNumbers.generate();

        number.getNumbers().forEach(e ->
                assertThat(e.getValue()).isBetween(1, 45)
        );
    }

    @RepeatedTest(10)
    void LottoNumber는_중복된_숫자가_없다() {
        LottoNumbers lottoNumbers = RandomLottoNumbers.generate();
        List<LottoNumber> numbers = lottoNumbers.getNumbers();

        numbers.forEach(number -> {
            List<LottoNumber> filteredNumbers = numbers.stream().filter(number::equals).toList();
            assertThat(filteredNumbers.size()).isEqualTo(1);
        });
    }
}