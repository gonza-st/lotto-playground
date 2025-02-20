package org.gonza.javaplayground.lotto.controller.response;

import java.util.List;

public class PurchaseRes {
    private final List<List<Integer>> lottoNumbers;

    public PurchaseRes(List<List<Integer>> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public List<List<Integer>> getLottoNumbers() {
        return lottoNumbers;
    }
}
