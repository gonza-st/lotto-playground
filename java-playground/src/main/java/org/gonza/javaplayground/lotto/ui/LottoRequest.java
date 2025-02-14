package org.gonza.javaplayground.lotto.ui;

import org.gonza.javaplayground.lotto.controller.Option;

import java.util.Optional;

public class LottoRequest {
    private final Option option;
    private final Object body;

    public LottoRequest(Option option) {
        this.option = option;
        this.body = null;
    }

    public LottoRequest(Option option, Object body) {
        this.option = option;
        this.body = body;
    }

    public Optional<Object> getBody() {
        return Optional.ofNullable(body);
    }

    public Option getOption() {
        return option;
    }
}