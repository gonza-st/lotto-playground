package org.gonza.javaplayground.lotto.domain.coin;

import org.gonza.javaplayground.lotto.domain.lotto.Payment;

public record Coin(
        Integer amount
) implements Payment {}
