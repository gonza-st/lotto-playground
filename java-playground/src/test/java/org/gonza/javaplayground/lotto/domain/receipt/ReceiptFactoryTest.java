package org.gonza.javaplayground.lotto.domain.receipt;

import org.gonza.javaplayground.lotto.domain.coin.Purchase;
import org.gonza.javaplayground.lotto.domain.lotto.LottoLineResult;
import org.gonza.javaplayground.lotto.domain.lotto.LottoResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReceiptFactoryTest {
    private static final List<Integer> PRICE_LIST = PriceTestFixtures.WINNING_PRICE_RANGE;

    private ReceiptFactory sut;

    @BeforeEach
    public void setUp() {
        WinningPriceTable table = new WinningPriceTable(PRICE_LIST);
        this.sut = new ReceiptFactory(table);
    }

    @Test
    public void should_return_winning_Receipt() {
        Purchase purchase = Purchase.of(1000);

        String lottoId = UUID.randomUUID().toString();
        LottoLineResult lineResult = new LottoLineResult(List.of(1,2,3));
        List<LottoLineResult> lineResults = List.of(lineResult);
        LottoResult result = new LottoResult(lottoId, lineResults);

        Receipt receipt = sut.printReceipt(purchase, result);

        Double expectedProfit = PRICE_LIST.get(3) / 1000.0;
        assertEquals(expectedProfit, receipt.getProfit());
    }
}
