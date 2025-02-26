package org.gonza.javaplayground.core;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LottoMachine {
    private final LottoNumberGenerator lottoNumberGenerator;

    public static final int PURCHASE_PRICE_UNIT = 1000;

    public LottoMachine(LottoNumberGenerator lottoNumberGenerator) {
        this.lottoNumberGenerator = lottoNumberGenerator;
    }

    public LottoTicket generateLottoTicket(Money purchasePrice) {
        validatePurchasePrice(purchasePrice);
        int numberOfPurchasedTicket = calculateNumberOfTickets(purchasePrice);

        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int i = 0; i < numberOfPurchasedTicket; i++) {
            lottoNumbers.add(lottoNumberGenerator.generateLottoNumber());
        }

        return new LottoTicket(lottoNumbers);
    }

    private int calculateNumberOfTickets(Money purchasePrice) {
        int numberOfPurchasedTicket = purchasePrice.value()
                .divide(BigDecimal.valueOf(PURCHASE_PRICE_UNIT))
                .intValue();
        return numberOfPurchasedTicket;
    }

    // todo 추후 기능 추가를 위해 구현해둠.
    public LottoNumber generateWinningLottoNumber() {
        return lottoNumberGenerator.generateLottoNumber();
    }

    private void validatePurchasePrice(Money purchasePrice) {
        if (purchasePrice.value().compareTo(BigDecimal.valueOf(PURCHASE_PRICE_UNIT)) < 0) {
            throw new IllegalArgumentException("로또는 1000원 이상부터 구매할 수 있습니다.");
        }

        if (purchasePrice.value().remainder(BigDecimal.valueOf(PURCHASE_PRICE_UNIT)).compareTo(BigDecimal.ZERO) != 0) {
            throw new IllegalArgumentException("로또 구매 금액은 1000원 단위여야 합니다.");
        }
    }
}
