package org.gonza.javaplayground.lotto.domain.lotto;

import org.gonza.javaplayground.lotto.domain.price.Item;

import java.util.*;

public class LottoLine {
    public static final Integer SIZE = 6;
    public static final Integer MAX_NUMBER = 45;
    public static final Integer MIN_NUMBER = 1;

    private List<Integer> line;

    private LottoLine(List<Integer> line) {
        this.line = line;
    }

    public List<Integer> getAllNumbers() {
        return line;
    }

    public LottoLineResult match(LottoLine candidate) {

        Set<Integer> input = new HashSet<>(candidate.line);
        Set<Integer> resultSet = new HashSet<>(line);
        resultSet.retainAll(input);

        List<Integer> result = new ArrayList<>(resultSet);

        return new Item(result);
    }

    public static LottoLine of(List<Integer> line) {
        validate(line);
        return new LottoLine(line);
    }

    private static void validate(List<Integer> numbers) {
        if (Objects.isNull(numbers)) {
            throw new IllegalArgumentException("The given number list is null");
        }

        if (!SIZE.equals(numbers.size())) {
            throw new IllegalArgumentException("numbers should be 6 digits length");
        }

        Set<Integer> uniqNumbers = new HashSet<>(numbers);
        if (numbers.size() != uniqNumbers.size()) {
            throw new IllegalArgumentException("Number must be uniq");
        }

        List<Integer> sorted = numbers.stream().sorted().toList();
        if (sorted.getLast() > MAX_NUMBER) {
            throw new IllegalArgumentException("max number is " + MAX_NUMBER);
        }

        if (sorted.getFirst() < MIN_NUMBER) {
            throw new IllegalArgumentException("min number is " + MIN_NUMBER);
        }
    }
}
