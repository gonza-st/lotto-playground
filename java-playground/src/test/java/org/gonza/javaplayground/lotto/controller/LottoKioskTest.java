package org.gonza.javaplayground.lotto.controller;

import org.gonza.javaplayground.lotto.controller.request.MatchReq;
import org.gonza.javaplayground.lotto.controller.request.PurchaseReq;
import org.gonza.javaplayground.lotto.controller.response.MatchRes;
import org.gonza.javaplayground.lotto.controller.response.PurchaseRes;
import org.gonza.javaplayground.lotto.domain.lotto.LottoFactory;
import org.gonza.javaplayground.lotto.domain.lotto.NumberGenerator;
import org.gonza.javaplayground.lotto.domain.lotto.NumberGeneratorStub;
import org.gonza.javaplayground.lotto.domain.price.MoneyExchanger;
import org.gonza.javaplayground.lotto.domain.price.PriceTestFixtures;
import org.gonza.javaplayground.lotto.domain.price.WinningPriceTable;
import org.gonza.javaplayground.lotto.repository.UsbStub;
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
        MoneyExchanger moneyExchanger = new MoneyExchanger(PRICE, winningPriceTable);

        NumberGenerator numberGenerator = new NumberGeneratorStub();
        LottoFactory lottoFactory = new LottoFactory(numberGenerator);

        Storage usb = new UsbStub();
        this.sut = new LottoKiosk(lottoFactory, moneyExchanger, usb);
    }

    @Test
    public void should_return_available_purchase_count_and_lotto_numbers() {
        List<Integer> lottoNumbers = NumberGeneratorStub.FIXED_LIST;

        PurchaseReq validReq = new PurchaseReq(2000);
        PurchaseRes response = sut.handlePurchase(validReq);

        assertEquals(2, response.getCount());
        assertEquals(List.of(lottoNumbers, lottoNumbers), response.getLottoNumbers());
    }

    @Test
    public void should_return_profit_by_percent_and_statistics() {
        MatchReq req = new MatchReq(List.of(1, 2, 3, 4, 5, 6));
        MatchRes res = sut.handleMatchNumbers(req);

        assertEquals(1.1, res.profit());
        assertEquals("1", res.statistics());
    }
}
