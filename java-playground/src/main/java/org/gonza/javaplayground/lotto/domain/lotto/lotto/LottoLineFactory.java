package org.gonza.javaplayground.lotto.domain.lotto.lotto;

import org.gonza.javaplayground.lotto.domain.lotto.LottoProperties;
import org.gonza.javaplayground.lotto.domain.lotto.NumberGenerator;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class LottoLineFactory {
    private final LottoProperties lottoProperties;
    private final NumberGenerator numberGenerator;

    public LottoLineFactory(LottoProperties lottoProperties, NumberGenerator numberGenerator) {
        this.lottoProperties = lottoProperties;
        this.numberGenerator = numberGenerator;
    }

    public LottoLine create() {
        List<Integer> lottoNumbers = generateNumbers();
        return this.create(lottoNumbers);
    }

    public LottoLine create(List<Integer> lottoNumbers) {
        validateLottoNumbers(lottoNumbers);
        return new LottoLine(lottoNumbers);
    }

    private List<Integer> generateNumbers() {
        List<Integer> numbers = numberGenerator.generate(
                lottoProperties.size(),
                lottoProperties.minNumber(),
                lottoProperties.maxNumber()
        );

        validateLottoNumbers(numbers);

        return numbers;
    }

    private void validateLottoNumbers(List<Integer> numbers) {
        if (Objects.isNull(numbers)) {
            throw new IllegalArgumentException("The given number list is null");
        }

        if (!lottoProperties.size().equals(numbers.size())) {
            throw new IllegalArgumentException("numbers should be 6 digits length");
        }

        Set<Integer> uniqNumbers = new HashSet<>(numbers);
        if (numbers.size() != uniqNumbers.size()) {
            throw new IllegalArgumentException("Number must be uniq");
        }

        List<Integer> sorted = numbers.stream().sorted().toList();
        if (sorted.getLast() > lottoProperties.maxNumber()) {
            throw new IllegalArgumentException("max number is " + lottoProperties.maxNumber());
        }

        if (sorted.getFirst() < lottoProperties.minNumber()) {
            throw new IllegalArgumentException("min number is " + lottoProperties.minNumber());
        }
    }

}
