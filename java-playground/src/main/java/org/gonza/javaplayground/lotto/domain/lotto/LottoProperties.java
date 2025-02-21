package org.gonza.javaplayground.lotto.domain.lotto;

public record LottoProperties(
        Integer pricePerLottoLine,
        Integer size,
        Integer minNumber,
        Integer maxNumber
) {
}
