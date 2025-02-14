package org.gonza.javaplayground.lotto.service.domain;

import java.util.*;

class LottoLine {
    private static final Integer SIZE = 6;
    private static final Integer MAX_NUMBER = 45;
    private static final Integer MIN_NUMBER = 1;

    private List<Integer> line;

    public LottoLine(List<Integer> line) {
        validate(line);

        this.line = line;
    }

    public List<Integer> match(List<Integer> numbers) {
        validate(numbers);

        Set<Integer> input = new HashSet<>(numbers);
        Set<Integer> resultSet = new HashSet<>(line);
        resultSet.retainAll(input);

        return new ArrayList<>(resultSet);
    }

    private void validate(List<Integer> numbers) {
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
