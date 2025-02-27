package org.gonza.javaplayground.lotto.domain.lotto.lotto;

import org.gonza.javaplayground.lotto.domain.lotto.LottoProperties;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoLineFactory {
    private final LottoProperties lottoProperties;
    private final LottoNumberFactory lottoNumberFactory;

    public LottoLineFactory(
            LottoProperties lottoProperties,
            LottoNumberFactory lottoNumberFactory
    ) {
        this.lottoProperties = lottoProperties;
        this.lottoNumberFactory = lottoNumberFactory;
    }

    public LottoLine create() {
        List<LottoNumber> lottoNumbers = lottoNumberFactory
                .createNumbers();

        return new LottoLine(lottoNumbers);
    }

    public LottoLine create(List<Integer> numbers) {
        validateNumbers(numbers);

        List<LottoNumber> lottoNumbers = lottoNumberFactory
                .createLottoNumbers(numbers);

        return new LottoLine(lottoNumbers);
    }

    private void validateNumbers(List<Integer> numbers) {
        if (!lottoProperties.size().equals(numbers.size())) {
            throw new IllegalArgumentException("numbers should be 6 digits length");
        }

        Set<Integer> uniqNumbers = new HashSet<>(numbers);
        if (numbers.size() != uniqNumbers.size()) {
            throw new IllegalArgumentException("Number must be uniq");
        }
    }
}
