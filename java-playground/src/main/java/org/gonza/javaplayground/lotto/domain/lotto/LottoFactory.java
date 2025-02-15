package org.gonza.javaplayground.lotto.domain.lotto;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class LottoFactory {
    private static final Integer SIZE = LottoLine.SIZE;
    private static final Integer MAX_NUMBER = LottoLine.MAX_NUMBER;
    private static final Integer MIN_NUMBER = LottoLine.MIN_NUMBER;

    private final NumberGenerator numberGenerator;

    public LottoFactory(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public Lotto createLotto(Integer count) {
        validate(count);

        List<List<Integer>> numbers = IntStream.range(0, count)
                .mapToObj((i) -> numberGenerator.generate(SIZE, MIN_NUMBER, MAX_NUMBER))
                .toList();

        return new Lotto(numbers);
    }

    private void validate(Integer count) {
        if (Objects.isNull(count)) {
            throw new IllegalArgumentException("Number cannot be null");
        }

        if (count <= 0) {
            throw new IllegalArgumentException("Number must be greater than zero");
        }
    }
}
