package org.gonza.javaplayground.lotto.service.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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

        List<Integer> matchingNumber = List.of(1,2,3,4,5,6);
        List<List<Integer>> result = lotto.match(matchingNumber);

        assertEquals(count, result.size());
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
