package org.gonza.javaplayground.lotto.domain.price;

import org.gonza.javaplayground.lotto.domain.lotto.Count;

import java.util.Objects;

public record Coin(Integer count) implements Count {

    public Coin {
        validate(count);
    }

    private void validate(Integer count) {
        if (Objects.isNull(count)) {
            throw new IllegalArgumentException("Count cannot be null");
        }

        if (count <= 0) {
            throw new IllegalArgumentException("Count must be greater than zero");
        }
    }
}