package org.gonza.javaplayground.lotto.domain.lotto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoFactoryTest {

    private LottoFactory sut;

    @BeforeEach
    public void setUp() {
        NumberGenerator numberGenerator = new NumberGeneratorStub();
        this.sut = new LottoFactory(numberGenerator);
    }

    @Test
    public void should_create_lotto_with_specific_count() {
        Integer count = 2;
        Lotto lotto = sut.createLotto(count);

        assertEquals(count, lotto.countLottoLines());
    }

    @Test
    public void should_throw_count_when_create_lotto_with_less_than_zero() {
        assertThrows(IllegalArgumentException.class, () -> sut.createLotto(0));
        assertThrows(IllegalArgumentException.class, () -> sut.createLotto(-1));
    }

    @Test
    public void should_throw_when_create_lotto_with_null() {
        assertThrows(IllegalArgumentException.class, () -> sut.createLotto(null));
    }
}
