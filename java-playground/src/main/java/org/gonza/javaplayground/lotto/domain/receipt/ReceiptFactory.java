package org.gonza.javaplayground.lotto.domain.receipt;

import org.gonza.javaplayground.lotto.domain.lotto.LottoResult;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReceiptFactory {
    private final WinningPrizeTable winningPrizeTable;

    public ReceiptFactory(WinningPrizeTable winningPrizeTable) {
        this.winningPrizeTable = winningPrizeTable;
    }

    public Receipt printReceipt(Payment payment, LottoResult lottoResult) {
        Map<Integer, List<List<Integer>>> mapByMatchingCount = lottoResult.getResults().stream()
                .collect(Collectors.groupingBy(List::size));

        List<Item> receiptItems = mapByMatchingCount.entrySet().stream()
                .map(entry -> this.createReceiptItem(entry.getKey(), entry.getValue().size()))
                .toList();

        return new Receipt(lottoResult.getLottoId(), receiptItems, payment.getCost());
    }

    private Item createReceiptItem(Integer matchingNumberCount, Integer count) {
        Integer prize = winningPrizeTable.getWinningPrice(matchingNumberCount);
        return new Item(matchingNumberCount, prize, count);
    }
}
