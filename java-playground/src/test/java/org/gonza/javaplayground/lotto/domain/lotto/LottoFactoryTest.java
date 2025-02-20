package org.gonza.javaplayground.lotto.domain.lotto;

import org.gonza.javaplayground.lotto.domain.payment.Cash;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottoFactoryTest {

    private LottoFactory sut;

    @BeforeEach
    public void setUp() {
        NumberGenerator numberGenerator = new NumberGeneratorStub();
        LottoProperties properties = new LottoProperties(1000, 6, 1, 45);
        this.sut = new LottoFactory(properties, numberGenerator);
    }

    @Test
    public void should_create_lotto_with_specific_count() {
        Cash cash = Cash.of(2000);
        Lotto lotto = sut.createLotto(cash);

        assertEquals(cash.calc(1000), lotto.countLottoLines());
    }
}
