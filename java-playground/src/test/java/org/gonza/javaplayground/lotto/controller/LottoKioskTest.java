package org.gonza.javaplayground.lotto.controller;

import org.gonza.javaplayground.lotto.controller.request.MatchRequest;
import org.gonza.javaplayground.lotto.controller.request.PurchaseRequest;
import org.gonza.javaplayground.lotto.controller.response.MatchResponse;
import org.gonza.javaplayground.lotto.controller.response.PurchaseResponse;
import org.gonza.javaplayground.lotto.domain.lotto.lotto.LottoFactory;
import org.gonza.javaplayground.lotto.domain.lotto.LottoProperties;
import org.gonza.javaplayground.lotto.domain.lotto.NumberGenerator;
import org.gonza.javaplayground.lotto.domain.lotto.NumberGeneratorStub;
import org.gonza.javaplayground.lotto.domain.lotto.lotto.LottoLineFactory;
import org.gonza.javaplayground.lotto.domain.lotto.lotto.LottoNumberFactory;
import org.gonza.javaplayground.lotto.domain.report.ReportFactory;
import org.gonza.javaplayground.lotto.domain.report.PriceTestFixtures;
import org.gonza.javaplayground.lotto.domain.report.WinningPrizeTable;
import org.gonza.javaplayground.lotto.repository.UsbStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottoKioskTest {
    private static final List<Integer> WINNING_PRICE_RANGE = PriceTestFixtures.WINNING_PRICE_RANGE;

    private LottoKiosk sut;

    @BeforeEach
    public void setUp() {
        WinningPrizeTable winningPrizeTable = new WinningPrizeTable(WINNING_PRICE_RANGE);
        ReportFactory reportFactory = new ReportFactory(winningPrizeTable);

        LottoProperties properties = new LottoProperties(1000, 6, 1, 45);
        NumberGenerator numberGenerator = new NumberGeneratorStub();
        LottoNumberFactory lottoNumberFactory = new LottoNumberFactory(properties, numberGenerator);
        LottoLineFactory lottoLineFactory = new LottoLineFactory(properties, lottoNumberFactory);
        LottoFactory lottoFactory = new LottoFactory(properties, lottoLineFactory);

        Storage usb = new UsbStub();
        this.sut = new LottoKiosk(lottoFactory, reportFactory, usb);
    }

    @Test
    public void should_return_available_purchase_count_and_lotto_numbers() {
        List<Integer> lottoNumbers = NumberGeneratorStub.FIXED_LIST;

        PurchaseRequest validReq = new PurchaseRequest(2000);
        PurchaseResponse response = sut.handlePurchase(validReq);

        assertEquals(List.of(lottoNumbers, lottoNumbers), response.lottoNumbers());
    }

    @Test
    public void should_return_profit_by_percent_and_statistics() {
        MatchRequest req = new MatchRequest(List.of(1, 2, 3, 4, 5, 6));
        MatchResponse res = sut.handleMatchNumbers(req);

        // TODO ("usb 목킹 필요")
    }
}
