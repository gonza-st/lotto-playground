package org.gonza.javaplayground.lotto.domain.receipt;

import org.gonza.javaplayground.lotto.domain.lotto.LottoLineResult;
import org.gonza.javaplayground.lotto.domain.lotto.LottoResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReceiptFactoryTest {
    private static final List<Integer> PRICE_LIST = PriceTestFixtures.WINNING_PRICE_RANGE;

    private ReceiptFactory sut;

    @BeforeEach
    public void setUp() {
        WinningPriceTable table = new WinningPriceTable(PRICE_LIST);
        this.sut = new ReceiptFactory(table);
    }

//    @Test
//    public void should_return_winning_Receipt() {
//        LottoLineResult lineResult = new LottoLineResult(List.of(1,2,3));
//        List<LottoLineResult> lineResults = List.of(lineResult);
//        LottoResult result = new LottoResult(lineResults);
//
//        Receipt receipt = sut.printReceipt(result);
//        assertEquals(PRICE_LIST.get(3), winning);
//    }
}
