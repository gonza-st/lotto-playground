package org.gonza.javaplayground.lotto.controller.response;

import java.util.List;

public record PurchaseResponse(List<List<Integer>> lottoNumbers) {
}
