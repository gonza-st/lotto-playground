package org.gonza.javaplayground.lotto.domain.receipt;

import org.gonza.javaplayground.lotto.domain.lotto.LottoLineResult;
import org.gonza.javaplayground.lotto.domain.lotto.LottoResult;

import java.util.List;
import java.util.Map;

public class ReceiptFactory {
    private final WinningPrizeTable winningPrizeTable;

    public ReceiptFactory(WinningPrizeTable winningPrizeTable) {
        this.winningPrizeTable = winningPrizeTable;
    }

    public Receipt printReceipt(Payment payment, LottoResult lottoResult) {
        Map<Integer, List<LottoLineResult>> mapByMatchingCount = lottoResult.getResults();

        List<Item> receiptItems = mapByMatchingCount.entrySet().stream()
                .map(this::createReceiptItem)
                .toList();

        return new Receipt(lottoResult.getLottoId(), receiptItems, payment.getCost());
    }

    private Item createReceiptItem(Map.Entry<Integer, List<LottoLineResult>> entry) {
        Integer matchingNumberCount = entry.getKey();
        Integer prize = winningPrizeTable.getWinningPrice(matchingNumberCount);
        Integer resultCount = entry.getValue().size();

        return new Item(matchingNumberCount, prize, resultCount);
    }
}
