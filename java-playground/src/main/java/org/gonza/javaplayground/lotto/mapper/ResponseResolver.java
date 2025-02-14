package org.gonza.javaplayground.lotto.mapper;

import org.gonza.javaplayground.lotto.ui.LottoResponse;

public interface ResponseResolver<T> {
    LottoResponse resolve(T response);
}
