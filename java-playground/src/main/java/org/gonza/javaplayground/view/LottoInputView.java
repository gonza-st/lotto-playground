package org.gonza.javaplayground.view;

import java.math.BigDecimal;
import java.util.List;

public interface LottoInputView {
    BigDecimal readPurchaseAmount();

    List<Integer> readWinningNumbers();

    void close();
}
