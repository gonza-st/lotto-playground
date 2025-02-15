package org.gonza.javaplayground.framework.mapper;

import org.gonza.javaplayground.lotto.ui.LottoResponse;

public interface ResponseResolver<T> {
    LottoResponse resolve(T response);
}
