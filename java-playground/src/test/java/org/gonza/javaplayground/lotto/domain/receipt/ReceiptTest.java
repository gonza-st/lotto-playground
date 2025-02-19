package org.gonza.javaplayground.lotto.domain.receipt;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReceiptTest {

    private static final String id = UUID.randomUUID().toString();

    @Nested
    class ProfitTest {
        @Test
        public void should_return_profit() {
            List<Item> items = List.of(
                    new Item(0, 500, 1)
            );
            Integer cost = 1000;

            Receipt receipt = new Receipt(id, items, cost);

            assertEquals(0.5, receipt.getProfit());
        }

        @Test
        public void should_return_if_no_winning_items_exist() {
            List<Item> items = List.of(
                    new Item(0, 0, 1)
            );
            Integer cost = 100;

            Receipt receipt = new Receipt(id, items, cost);

            assertEquals(0.0, receipt.getProfit());
        }
    }
}
