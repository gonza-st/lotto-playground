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
        return generateLottoTicket(purchasePrice, List.of());
    }

    public LottoTicket generateLottoTicket(Money purchasePrice, List<LottoNumber> manualLottoNumbers) {
        validatePurchasePrice(purchasePrice);

        int totalTickets = calculateNumberOfTickets(purchasePrice);
        int autoTickets = totalTickets - manualLottoNumbers.size();

        if (autoTickets < 0) {
            throw new IllegalArgumentException("수동 로또 수가 총 구매 가능한 로또 수를 초과합니다.");
        }

        List<LottoNumber> lottoNumbers = new ArrayList<>(manualLottoNumbers);

        for (int i = 0; i < autoTickets; i++) {
            lottoNumbers.add(lottoNumberGenerator.generateLottoNumber());
        }

        return new LottoTicket(lottoNumbers);
    }

    private int calculateNumberOfTickets(Money purchasePrice) {
        return purchasePrice.value()
                .divide(BigDecimal.valueOf(PURCHASE_PRICE_UNIT))
                .intValue();
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
