package org.gonza.javaplayground.lotto.repository;

import org.gonza.javaplayground.lotto.controller.Storage;
import org.gonza.javaplayground.lotto.domain.lotto.Lotto;

import java.util.*;

public class USB implements Storage {
    private static final Stack<Lotto> usb = new Stack<>();

    @Override
    public void save(Lotto lotto) {
        usb.push(lotto);
    }

    @Override
    public Lotto readLast() {
        return usb.peek();
    }
}
