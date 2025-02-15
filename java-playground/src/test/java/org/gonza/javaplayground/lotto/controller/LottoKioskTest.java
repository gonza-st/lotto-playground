package org.gonza.javaplayground.lotto.controller;

import org.gonza.javaplayground.lotto.controller.request.PurchaseReq;
import org.gonza.javaplayground.lotto.controller.response.PurchaseRes;
import org.gonza.javaplayground.lotto.service.lotto.LottoFactory;
import org.gonza.javaplayground.lotto.service.lotto.NumberGenerator;
import org.gonza.javaplayground.lotto.service.lotto.NumberGeneratorStub;
import org.gonza.javaplayground.lotto.service.price.PriceCalculator;
import org.gonza.javaplayground.lotto.service.price.PriceTestFixtures;
import org.gonza.javaplayground.lotto.service.price.WinningPriceTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottoKioskTest {
    private static final Integer PRICE = PriceTestFixtures.PRICE;
    private static final List<Integer> WINNING_PRICE_RANGE = PriceTestFixtures.WINNING_PRICE_RANGE;

    private LottoKiosk sut;

    @BeforeEach
    public void setUp() {
        WinningPriceTable winningPriceTable = new WinningPriceTable(WINNING_PRICE_RANGE);
        PriceCalculator priceCalculator = new PriceCalculator(PRICE, winningPriceTable);

        NumberGenerator numberGenerator = new NumberGeneratorStub();
        LottoFactory lottoFactory = new LottoFactory(numberGenerator);
        this.sut = new LottoKiosk(lottoFactory, priceCalculator);
    }

    @Test
    public void should_return_available_purchase_count_and_lotto_numbers() {
        List<Integer> lottoNumbers = NumberGeneratorStub.FIXED_LIST;

        PurchaseReq validReq = new PurchaseReq(2000);
        PurchaseRes response = sut.handlePurchase(validReq);

        assertEquals(2, response.getCount());
        assertEquals(List.of(lottoNumbers, lottoNumbers), response.getLottoNumbers());
    }
}
