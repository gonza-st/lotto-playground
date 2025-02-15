package org.gonza.javaplayground.lotto.controller.response;

import java.util.List;

public class PurchaseRes {
    private final Integer count;
    private final List<List<Integer>> lottoNumbers;

    private PurchaseRes(Integer count, List<List<Integer>> lottoNumbers) {
        this.count = count;
        this.lottoNumbers = lottoNumbers;
    }

    public Integer getCount() {
        return count;
    }

    public List<List<Integer>> getLottoNumbers() {
        return lottoNumbers;
    }

    public static PurchaseRes of(Integer count, List<List<Integer>> lottoNumbers) {
        return new PurchaseRes(count, lottoNumbers);
    }
}
