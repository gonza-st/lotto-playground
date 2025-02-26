package org.gonza.javaplayground.lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LottoTest {

    @Test
    void Lotto는_생성시_ISSUED_다() {
        LottoNumber numbers = LottoNumber.of(List.of(1, 2, 3, 4, 5, 6));

        Lotto lotto = Lotto.of(numbers);

        assertTrue(lotto.isIssued());
    }
}