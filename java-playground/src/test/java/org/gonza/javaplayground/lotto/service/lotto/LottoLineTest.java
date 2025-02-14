package org.gonza.javaplayground.lotto.service.lotto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoLineTest {
    private static final Integer MAX_NUMBER = 45;
    private static final Integer MIN_NUMBER = 1;

    private LottoLine sut;

    @BeforeEach
    public void setUp() {
        List<Integer> givenNumbers = List.of(1, 2, 3, 4, 5, 6);
        this.sut = new LottoLine(givenNumbers);
    }

    @Test
    public void should_throw_if_given_line_is_null() {
        assertThrows(IllegalArgumentException.class, () -> new LottoLine(null));
    }

    @Test
    public void should_throw_if_given_line_size_is_not_valid() {
        List<Integer> shortNumbers = List.of(1, 2, 3, 4, 5);
        List<Integer> longNumbers = List.of(1, 2, 3, 4, 5, 6, 7);

        assertThrows(IllegalArgumentException.class, () -> new LottoLine(shortNumbers));
        assertThrows(IllegalArgumentException.class, () -> new LottoLine(longNumbers));
    }

    @Test
    public void should_return_all_numbers_when_every_number_matches() {
        List<Integer> allMatchingNumbers = List.of(1, 2, 3, 4, 5, 6);

        List<Integer> matchedNumber = sut.match(allMatchingNumbers);
        assertEquals(allMatchingNumbers, matchedNumber);
    }

    @Test
    public void should_return_matched_number_list() {
        List<Integer> someMatchingNumbers = List.of(1, 2, 3, 7, 8, 9);

        List<Integer> matchedNumber = sut.match(someMatchingNumbers);
        assertEquals(List.of(1, 2, 3), matchedNumber);
    }

    @Test
    public void should_return_empty_list_if_nothing_matches() {
        List<Integer> someMatchingNumbers = List.of(7, 8, 9, 10, 11, 12);

        List<Integer> matchedNumber = sut.match(someMatchingNumbers);
        assertEquals(List.of(), matchedNumber);
    }

    @Test
    public void should_throw_illegal_argument_exception_when_number_is_not_6_digits() {
        List<Integer> shortList = List.of(1, 2, 3);
        assertThrows(IllegalArgumentException.class,
                () -> sut.match(shortList)
        );

        List<Integer> emptyList = List.of();
        assertThrows(IllegalArgumentException.class,
                () -> sut.match(emptyList)
        );
    }

    @Test
    public void should_throw_illegal_argument_exception_when_number_is_duplicated() {
        List<Integer> tooSmallNumberList = List.of(1, 1, 2, 3, 4, 5);
        assertThrows(IllegalArgumentException.class,
                () -> sut.match(tooSmallNumberList)
        );
    }

    @Test
    public void should_throw_illegal_argument_exception_when_max_number_is_larger_than_45() {
        List<Integer> tooLargeNumberList = List.of(MAX_NUMBER + 1, 1, 2, 3, 4, 5);
        assertThrows(IllegalArgumentException.class,
                () -> sut.match(tooLargeNumberList)
        );
    }

    @Test
    public void should_throw_illegal_argument_exception_when_min_number_is_smaller_than_45() {
        List<Integer> tooSmallNumberList = List.of(MIN_NUMBER - 1, 1, 2, 3, 4, 5);
        assertThrows(IllegalArgumentException.class,
                () -> sut.match(tooSmallNumberList)
        );
    }
}
