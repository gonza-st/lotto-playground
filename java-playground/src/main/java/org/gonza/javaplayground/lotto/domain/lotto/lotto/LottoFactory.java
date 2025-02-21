package org.gonza.javaplayground.lotto.domain.lotto.lotto;

import org.gonza.javaplayground.lotto.domain.lotto.BuyingCount;
import org.gonza.javaplayground.lotto.domain.lotto.LottoProperties;

import java.util.List;
import java.util.stream.IntStream;

public class LottoFactory {
    private final LottoProperties lottoProperties;
    private final LottoLineFactory lottoLineFactory;

    public LottoFactory(LottoProperties lottoProperties, LottoLineFactory lottoLineFactory) {
        this.lottoProperties = lottoProperties;
        this.lottoLineFactory = lottoLineFactory;
    }

    public LottoLine createLottoLine(List<Integer> numbers) {
        return lottoLineFactory.create(numbers);
    }

    public Lotto createLotto(BuyingCount buyingCount) {
        Integer amount = buyingCount.calculate(lottoProperties.pricePerLottoLine());

        List<LottoLine> lottoLines = IntStream.range(0, amount)
                .mapToObj((i) -> lottoLineFactory.create())
                .toList();

        return new Lotto(lottoLines);
    }
}
