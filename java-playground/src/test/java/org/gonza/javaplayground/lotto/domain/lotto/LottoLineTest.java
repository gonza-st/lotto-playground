package org.gonza.javaplayground.lotto.domain.lotto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
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
        this.sut = LottoLine.of(givenNumbers);
    }

    @Nested
    class MatchTest {
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
    }

    @Nested
    class CreationTest {
        @Test
        public void should_return_valid_lotto_line() {
            List<Integer> validNumbers = List.of(1, 2, 3, 4, 5, 6);
            LottoLine lottoLine = LottoLine.of(validNumbers);

            assertEquals(validNumbers, lottoLine.getAllNumbers());
        }

        @Test
        public void should_throw_if_given_line_is_null() {
            assertThrows(IllegalArgumentException.class, () -> LottoLine.of(null));
        }

        @Test
        public void should_throw_if_given_line_size_is_not_valid() {
            List<Integer> shortNumbers = List.of(1, 2, 3, 4, 5);
            List<Integer> longNumbers = List.of(1, 2, 3, 4, 5, 6, 7);

            assertThrows(IllegalArgumentException.class, () -> LottoLine.of(shortNumbers));
            assertThrows(IllegalArgumentException.class, () -> LottoLine.of(longNumbers));
        }


        @Test
        public void should_throw_illegal_argument_exception_when_number_is_not_6_digits() {
            List<Integer> shortList = List.of(1, 2, 3);
            assertThrows(IllegalArgumentException.class,
                    () -> LottoLine.of(shortList)
            );

            List<Integer> emptyList = List.of();
            assertThrows(IllegalArgumentException.class,
                    () -> LottoLine.of(emptyList)
            );
        }

        @Test
        public void should_throw_illegal_argument_exception_when_number_is_duplicated() {
            List<Integer> tooSmallNumberList = List.of(1, 1, 2, 3, 4, 5);
            assertThrows(IllegalArgumentException.class,
                    () -> LottoLine.of(tooSmallNumberList)
            );
        }

        @Test
        public void should_throw_illegal_argument_exception_when_max_number_is_larger_than_45() {
            List<Integer> tooLargeNumberList = List.of(MAX_NUMBER + 1, 1, 2, 3, 4, 5);
            assertThrows(IllegalArgumentException.class,
                    () -> LottoLine.of(tooLargeNumberList)
            );
        }

        @Test
        public void should_throw_illegal_argument_exception_when_min_number_is_smaller_than_45() {
            List<Integer> tooSmallNumberList = List.of(MIN_NUMBER - 1, 1, 2, 3, 4, 5);
            assertThrows(IllegalArgumentException.class,
                    () -> LottoLine.of(tooSmallNumberList)
            );
        }
    }
}
