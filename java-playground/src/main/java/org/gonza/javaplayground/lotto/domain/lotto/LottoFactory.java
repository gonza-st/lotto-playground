package org.gonza.javaplayground.lotto.domain.lotto;

import java.util.List;
import java.util.stream.IntStream;

public class LottoFactory {
    private static final Integer PRICE_PER_LOTTO_LINE = LottoLine.PRICE;
    private static final Integer SIZE = LottoLine.SIZE;
    private static final Integer MAX_NUMBER = LottoLine.MAX_NUMBER;
    private static final Integer MIN_NUMBER = LottoLine.MIN_NUMBER;

    private final NumberGenerator numberGenerator;

    public LottoFactory(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public Lotto createLotto(Count count) {
        Integer amount = count.calc(PRICE_PER_LOTTO_LINE);
        List<List<Integer>> numbers = IntStream.range(0, amount)
                .mapToObj((i) -> numberGenerator.generate(SIZE, MIN_NUMBER, MAX_NUMBER))
                .toList();

        return Lotto.of(numbers);
    }
}
