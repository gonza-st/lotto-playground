package org.gonza.javaplayground.core;

import java.math.BigDecimal;

public record Money(BigDecimal value) {
    public Money {
        validate(value);
    }

    private void validate(BigDecimal value) {
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("금액은 0보다 작을 수 없습니다.");
        }
    }
}
