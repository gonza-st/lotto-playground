package org.gonza.javaplayground.lotto.domain.utils;

import org.gonza.javaplayground.lotto.domain.lotto.NumberGenerator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomNumberGenerator implements NumberGenerator {
    @Override
    public List<Integer> generate(Integer size, Integer min, Integer max) {
        validateInteger(size, min, max);

        List<Integer> shuffledNumbers = generateShuffledNumbers(min, max);
        List<Integer> limited = limitedNumbers(shuffledNumbers, size);

        return limited;
    }

    private void validateInteger(Integer size, Integer min, Integer max) {
        if (Objects.isNull(size) || Objects.isNull(min) || Objects.isNull(max)) {
            throw new IllegalArgumentException("min and/or max must be non-null");
        }

        if (size <= 0 || min <= 0 || max <= 0) {
            throw new IllegalArgumentException("min and max must be greater than zero");
        }

        if (min > max) {
            throw new IllegalArgumentException("min must be less than max");
        }
    }


    private List<Integer> generateShuffledNumbers(Integer min, Integer max) {
        List<Integer> numbers = IntStream.range(min, max + 1)
                .boxed()
                .collect(Collectors.toList());

        Collections.shuffle(numbers);
        return numbers;
    }

    private List<Integer> limitedNumbers(List<Integer> numbers, Integer limit) {
        return numbers.stream()
                .limit(limit)
                .toList();
    }
}
