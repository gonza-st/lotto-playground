package org.gonza.javaplayground.lotto.mapper;

import org.gonza.javaplayground.lotto.ui.LottoRequest;

public interface RequestResolver<T> {
    T resolve(LottoRequest lottoRequest);
}
