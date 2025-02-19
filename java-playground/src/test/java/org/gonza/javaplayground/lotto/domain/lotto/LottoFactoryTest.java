package org.gonza.javaplayground.lotto.domain.lotto;

import org.gonza.javaplayground.lotto.domain.coin.Coin;
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
        Coin coin = new Coin(2);
        Lotto lotto = sut.createLotto(coin);

        assertEquals(coin.count(), lotto.countLottoLines());
    }
}
