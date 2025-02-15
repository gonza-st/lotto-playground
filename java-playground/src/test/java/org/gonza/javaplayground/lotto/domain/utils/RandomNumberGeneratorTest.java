package org.gonza.javaplayground.lotto.domain.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RandomNumberGeneratorTest {

    private RandomNumberGenerator sut;

    @BeforeEach
    public void setUp() {
        sut = new RandomNumberGenerator();
    }

    @Test
    public void should_generate_number_between_min_max_and_specific_size() {
        Integer size = 6;
        Integer min = 1;
        Integer max = 6;

        List<Integer> numbers = sut.generate(size, min, max);

        Integer minNumber = numbers.stream().min(Integer::compareTo).get();
        Integer maxNumber = numbers.stream().max(Integer::compareTo).get();

        assertEquals(min, minNumber);
        assertEquals(max, maxNumber);
        assertEquals(size, numbers.size());
    }

    @Test
    public void should_throw_if_min_is_larger_than_max() {
        Integer size = 6;
        Integer min = 6;
        Integer max = 1;

        assertThrows(IllegalArgumentException.class, () -> sut.generate(size, min, max));
    }
}
