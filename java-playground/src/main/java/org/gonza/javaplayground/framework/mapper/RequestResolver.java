package org.gonza.javaplayground.framework.mapper;

import org.gonza.javaplayground.lotto.ui.LottoRequest;

public interface RequestResolver<T> {
    T resolve(LottoRequest lottoRequest);
}
