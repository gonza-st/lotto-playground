package org.gonza.javaplayground.lotto.controller;

import org.gonza.javaplayground.framework.mapper.Advice;
import org.gonza.javaplayground.lotto.ui.LottoResponse;

public class ErrorHandler implements Advice {
    @Override
    public LottoResponse advice(Exception exception) {

        return new LottoResponse(400, exception.getClass().getSimpleName());
    }
}
