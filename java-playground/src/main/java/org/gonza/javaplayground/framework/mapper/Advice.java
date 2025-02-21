package org.gonza.javaplayground.framework.mapper;

import org.gonza.javaplayground.lotto.ui.LottoResponse;

public interface Advice {
    LottoResponse advice(Exception exception);
}
