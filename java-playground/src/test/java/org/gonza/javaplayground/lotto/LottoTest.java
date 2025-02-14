package org.gonza.javaplayground.lotto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LottoTest {

    @Test
    void Lotto는_생성시_ISSUED_다() {
        LottoNumbers numbers = new LottoNumbers();

        Lotto lotto = Lotto.of(numbers);

        assertTrue(lotto.isIssued());
    }
}