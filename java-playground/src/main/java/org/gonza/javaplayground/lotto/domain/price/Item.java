package org.gonza.javaplayground.lotto.domain.price;

import org.gonza.javaplayground.lotto.domain.lotto.LottoLineResult;

import java.util.List;

public class Item implements LottoLineResult {
    private final List<Integer> result;

    public Item(List<Integer> results) {
        this.result = results;
    }

    @Override
    public List<Integer> getResult() {
        return result;
    }
}
