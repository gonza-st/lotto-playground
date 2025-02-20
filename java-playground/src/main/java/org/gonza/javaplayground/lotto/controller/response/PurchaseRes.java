package org.gonza.javaplayground.lotto.controller.response;

import java.util.List;

public record PurchaseRes(List<List<Integer>> lottoNumbers) {
}
