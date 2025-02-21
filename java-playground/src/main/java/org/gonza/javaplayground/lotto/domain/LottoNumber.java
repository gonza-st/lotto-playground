package org.gonza.javaplayground.lotto.domain;

import java.util.Collections;
import java.util.List;

public class LottoNumber {
    public static LottoNumber of(List<Integer> numbers) {
        validateNumbers(numbers);

        return new LottoNumber(numbers);
    }

    private static void validateNumbers(List<Integer> numbers) {
        validateSize(numbers);
        numbers.forEach(LottoNumber::validateRange);
    }

    private static void validateSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("Number must be 6 numbers");
        }
    }

    private static void validateRange(Integer number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("Number must be between 1 and 45");
        }
    }

    private final List<Integer> numbers;

    private LottoNumber(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public int size() {
        return numbers.size();
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(this.numbers);
    }

    public int matchBy(LottoNumber lottoNumber) {
        return this.numbers.stream()
                .filter(lottoNumber.numbers::contains)
                .toList()
                .size();
    }
}
