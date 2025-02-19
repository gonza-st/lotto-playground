package org.gonza.javaplayground.lotto.domain.receipt;

import java.util.List;
import java.util.Map;

public class Receipt {
    private final List<Item> items;

    public Receipt(List<Item> items) {
        this.items = items;
    }

    public List<Map<String, Integer>> getItems() {
        return items.stream().map(Item::toMap).toList();
    }
}
