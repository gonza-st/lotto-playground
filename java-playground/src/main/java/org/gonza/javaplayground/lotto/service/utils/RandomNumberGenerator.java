package org.gonza.javaplayground.lotto.service.utils;

import org.gonza.javaplayground.lotto.service.domain.NumberGenerator;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomNumberGenerator implements NumberGenerator {
    @Override
    public List<Integer> generate(Integer size, Integer min, Integer max) {
        if (min > max) {
            throw new IllegalArgumentException("min must be less than max");
        }

        List<Integer> shuffledNumbers = generateShuffledNumbers(min, max);
        List<Integer> limited = limitedNumbers(shuffledNumbers, size);

        return limited;
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
