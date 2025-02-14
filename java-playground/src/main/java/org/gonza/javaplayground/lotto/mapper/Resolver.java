package org.gonza.javaplayground.lotto.mapper;

import org.gonza.javaplayground.lotto.ui.LottoRequest;

public interface Resolver<T> {
    T resolve(LottoRequest lottoRequest);
}
