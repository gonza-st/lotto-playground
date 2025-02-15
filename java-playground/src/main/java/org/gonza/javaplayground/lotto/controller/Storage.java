package org.gonza.javaplayground.lotto.controller;

import org.gonza.javaplayground.lotto.domain.lotto.Lotto;

public interface Storage {
    void save(Lotto lotto);

    Lotto readLast();
}
