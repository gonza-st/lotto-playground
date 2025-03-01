package org.gonza.javaplayground.lotto.domain;

import java.util.Collections;
import java.util.List;

import org.gonza.javaplayground.lotto.LottoConstant;

public class LottoNumbers {

    public static LottoNumbers of(List<Integer> numbers) {
        validateNumbers(numbers);
        List<LottoNumber> lottoNumbers = numbers.stream()
            .map(LottoNumber::of)
            .toList();

        return new LottoNumbers(lottoNumbers);
    }

    private static void validateNumbers(List<Integer> numbers) {
        validateSize(numbers);
    }

    private static void validateSize(List<Integer> numbers) {
        if (numbers.size() != LottoConstant.LOTTO_SIZE) {
            throw new IllegalArgumentException("Number must be 6 numbers");
        }
    }

    private final List<LottoNumber> lottoNumbers;

    private LottoNumbers(List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public int size() {
        return lottoNumbers.size();
    }

    public List<LottoNumber> getNumbers() {
        return Collections.unmodifiableList(this.lottoNumbers);
    }

    public int matchBy(LottoNumbers lottoNumbers) {
        return this.lottoNumbers.stream()
                .filter(lottoNumbers::contains)
                .toList()
                .size();
    }

    private boolean contains(LottoNumber lottoNumber) {
        return this.lottoNumbers.stream()
            .anyMatch(lottoNumber::equals);
    }
}
