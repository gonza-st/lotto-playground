package org.gonza.javaplayground.lotto.domain.lotto;

import org.gonza.javaplayground.lotto.domain.price.Coin;

import java.util.List;
import java.util.stream.IntStream;

public class LottoFactory {
    private static final Integer SIZE = LottoLine.SIZE;
    private static final Integer MAX_NUMBER = LottoLine.MAX_NUMBER;
    private static final Integer MIN_NUMBER = LottoLine.MIN_NUMBER;

    private final NumberGenerator numberGenerator;

    public LottoFactory(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public Lotto createLotto(Coin coin) {
        List<List<Integer>> numbers = IntStream.range(0, coin.count())
                .mapToObj((i) -> numberGenerator.generate(SIZE, MIN_NUMBER, MAX_NUMBER))
                .toList();

        return new Lotto(numbers);
    }
}
