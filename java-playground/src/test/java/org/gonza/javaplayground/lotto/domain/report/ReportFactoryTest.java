package org.gonza.javaplayground.lotto.domain.report;

import org.gonza.javaplayground.lotto.domain.payment.Cash;
import org.gonza.javaplayground.lotto.domain.lotto.result.LottoLineResult;
import org.gonza.javaplayground.lotto.domain.lotto.result.LottoResultList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReportFactoryTest {
    private static final List<Integer> PRICE_LIST = PriceTestFixtures.WINNING_PRICE_RANGE;

    private ReportFactory sut;

    @BeforeEach
    public void setUp() {
        WinningPrizeTable table = new WinningPrizeTable(PRICE_LIST);
        this.sut = new ReportFactory(table);
    }

    @Test
    public void should_return_winning_Receipt() {
        Payment payment = Cash.of(1000);

        String lottoId = UUID.randomUUID().toString();
        LottoLineResult lineResult = new LottoLineResult(List.of(1,2,3));
        List<LottoLineResult> lineResults = List.of(lineResult);
        LottoResultList result = LottoResultList.of(lottoId, lineResults);

        Report report = sut.printReport(payment, result);

        Double expectedProfit = PRICE_LIST.get(3) / 1000.0;
        assertEquals(expectedProfit, report.getProfit());
    }
}
