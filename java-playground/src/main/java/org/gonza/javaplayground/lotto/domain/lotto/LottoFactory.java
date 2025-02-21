package org.gonza.javaplayground.lotto.domain.lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.IntStream;

public class LottoFactory {
    private final LottoProperties lottoProperties;
    private final NumberGenerator numberGenerator;

    public LottoFactory(LottoProperties lottoProperties, NumberGenerator numberGenerator) {
        this.lottoProperties = lottoProperties;
        this.numberGenerator = numberGenerator;
    }

    public Lotto createLotto(BuyingCount buyingCount) {
        Integer amount = buyingCount.calculate(lottoProperties.pricePerLottoLine());
        List<List<Integer>> numbers = IntStream.range(0, amount)
                .mapToObj(this::generateNumbers)
                .peek(this::validateLottoLine)
                .toList();

        if (numbers.isEmpty()) {
            throw new IllegalArgumentException("The given numbers list is empty");
        }

        return new Lotto(numbers);
    }

    private List<Integer> generateNumbers(Integer ignore) {
        return numberGenerator.generate(lottoProperties.size(), lottoProperties.minNumber(), lottoProperties.maxNumber());
    }

    private void validateLottoLine(List<Integer> numbers) {
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
