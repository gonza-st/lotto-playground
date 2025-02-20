package org.gonza.javaplayground.lotto.domain;

import java.util.List;

public class LottoNumber {
    public static LottoNumber of(List<Integer> numbers) {
        return new LottoNumber(numbers);
    }

    private final List<Integer> numbers;

    private LottoNumber(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public int size() {
        return numbers.size();
    }

    public List<Integer> getNumbers() {
        return this.numbers;
    }

    public int matchBy(LottoNumber lottoNumber) {
        return this.numbers.stream()
                .filter(lottoNumber.numbers::contains)
                .toList()
                .size();
    }
}
