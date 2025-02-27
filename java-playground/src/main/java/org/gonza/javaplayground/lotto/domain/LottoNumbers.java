package org.gonza.javaplayground.lotto.domain;

import org.gonza.javaplayground.lotto.LottoConstant;

import java.util.Collections;
import java.util.List;

public class LottoNumbers {

    public static LottoNumbers of(List<Integer> numbers) {
        validateNumbers(numbers);

        return new LottoNumbers(numbers);
    }

    private static void validateNumbers(List<Integer> numbers) {
        validateSize(numbers);
        numbers.forEach(LottoNumbers::validateRange);
    }

    private static void validateSize(List<Integer> numbers) {
        if (numbers.size() != LottoConstant.LOTTO_SIZE) {
            throw new IllegalArgumentException("Number must be 6 numbers");
        }
    }

    private static void validateRange(Integer number) {
        if (number < LottoConstant.LOTTO_MIN_NUMBER || number > LottoConstant.LOTTO_MAX_NUMBER) {
            throw new IllegalArgumentException("Number must be between 1 and 45");
        }
    }

    private final List<Integer> numbers;

    private LottoNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public int size() {
        return numbers.size();
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(this.numbers);
    }

    public int matchBy(LottoNumbers lottoNumbers) {
        return this.numbers.stream()
                .filter(lottoNumbers.numbers::contains)
                .toList()
                .size();
    }
}
