package org.gonza.javaplayground.lotto.domain;

import java.util.Collections;
import java.util.List;

import org.gonza.javaplayground.lotto.LottoConstant;

public class LottoNumbers {

    public static LottoNumbers of(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = convertLottoNumberList(numbers);

        return new LottoNumbers(lottoNumbers, LottoType.AUTOMATIC);
    }

    public static LottoNumbers manualOf(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = convertLottoNumberList(numbers);

        return new LottoNumbers(lottoNumbers, LottoType.MANUAL);
    }

    private static List<LottoNumber> convertLottoNumberList(List<Integer> numbers) {
        validateNumbers(numbers);
        return numbers.stream()
            .map(LottoNumber::of)
            .toList();
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
    private final LottoType type;

    private LottoNumbers(List<LottoNumber> lottoNumbers, LottoType type) {
        this.lottoNumbers = lottoNumbers;
        this.type = type;
    }

    public int size() {
        return lottoNumbers.size();
    }

    public List<LottoNumber> getNumbers() {
        return Collections.unmodifiableList(this.lottoNumbers);
    }

    public int matchBy(LottoNumbers lottoNumbers) {
        return this.lottoNumbers.stream()
                .filter(lottoNumbers::matchBy)
                .toList()
                .size();
    }

    public boolean matchBy(LottoNumber lottoNumber) {
        return this.lottoNumbers.stream()
            .anyMatch(lottoNumber::equals);
    }

    public boolean isManual() {
        return this.type.isManual();
    }

    public boolean isAutomatic() {
        return this.type.isAutomatic();
    }
}
