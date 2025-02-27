package org.gonza.javaplayground.lotto.domain.lotto.lotto;

import org.gonza.javaplayground.lotto.domain.lotto.LottoProperties;
import org.gonza.javaplayground.lotto.domain.lotto.NumberGenerator;

import java.util.*;

public class LottoNumberFactory {
    private final LottoProperties properties;
    private final NumberGenerator numberGenerator;

    public LottoNumberFactory(
            LottoProperties properties,
            NumberGenerator numberGenerator
    ) {
        this.properties = properties;
        this.numberGenerator = numberGenerator;
    }

    public List<LottoNumber> createLottoNumbers(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
                .peek(this::validateLottoNumber)
                .map(LottoNumber::new).toList();

        return lottoNumbers;
    }

    public List<LottoNumber> createNumbers() {
        List<Integer> numbers = numberGenerator.generate(
                properties.size(),
                properties.minNumber(),
                properties.maxNumber()
        );

        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::new).toList();

        return lottoNumbers;
    }

    private void validateLottoNumber(Integer number) {
        if (number > properties.maxNumber()) {
            throw new IllegalArgumentException("max number is " + properties.maxNumber());
        }

        if (number < properties.minNumber()) {
            throw new IllegalArgumentException("min number is " + properties.minNumber());
        }
    }
}
