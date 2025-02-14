package org.gonza.javaplayground.lotto.controller.response.resolver;

import org.gonza.javaplayground.lotto.controller.response.ErrorRes;
import org.gonza.javaplayground.lotto.mapper.ResponseResolver;
import org.gonza.javaplayground.lotto.ui.LottoResponse;

public class ErrorResponseResolver implements ResponseResolver<ErrorRes>{
    @Override
    public LottoResponse resolve(ErrorRes response) {
        return null;
    }
}
