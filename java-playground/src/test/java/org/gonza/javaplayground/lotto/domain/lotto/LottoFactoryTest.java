package org.gonza.javaplayground.lotto.domain.lotto;

import org.gonza.javaplayground.lotto.domain.coin.Purchase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottoFactoryTest {

    private LottoFactory sut;

    @BeforeEach
    public void setUp() {
        NumberGenerator numberGenerator = new NumberGeneratorStub();
        this.sut = new LottoFactory(numberGenerator);
    }

    @Test
    public void should_create_lotto_with_specific_count() {
        Purchase purchase = Purchase.of(2000);
        Lotto lotto = sut.createLotto(purchase);

        assertEquals(purchase.calc(1000), lotto.countLottoLines());
    }
}
