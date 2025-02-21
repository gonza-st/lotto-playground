package org.gonza.javaplayground.lotto.domain.lotto;

import org.gonza.javaplayground.lotto.domain.lotto.result.LottoLineResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoLineTest {
    private LottoLine sut;

    @BeforeEach
    public void setUp() {
        List<Integer> givenNumbers = List.of(1, 2, 3, 4, 5, 6);
        this.sut = new LottoLine(givenNumbers);
    }

    @Nested
    class MatchTest {
        @Test
        public void should_return_all_numbers_when_every_number_matches() {
            List<Integer> allMatchingNumbers = List.of(1, 2, 3, 4, 5, 6);
            LottoLine line = new LottoLine(allMatchingNumbers);

            LottoLineResult matchedNumber = sut.match(line);
            assertEquals(allMatchingNumbers, matchedNumber.getResult());
        }

        @Test
        public void should_return_matched_number_list() {
            List<Integer> someMatchingNumbers = List.of(1, 2, 3, 7, 8, 9);
            LottoLine line = new LottoLine(someMatchingNumbers);

            LottoLineResult matchedNumber = sut.match(line);
            assertEquals(List.of(1, 2, 3), matchedNumber.getResult());
        }

        @Test
        public void should_return_empty_list_if_nothing_matches() {
            List<Integer> nothingMatchingNumbers = List.of(7, 8, 9, 10, 11, 12);
            LottoLine line = new LottoLine(nothingMatchingNumbers);

            LottoLineResult matchedNumber = sut.match(line);
            assertEquals(List.of(), matchedNumber.getResult());
        }
    }

    @Nested
    class CreationTest {
        @Test
        public void should_return_valid_lotto_line() {
            List<Integer> validNumbers = List.of(1, 2, 3, 4, 5, 6);
            LottoLine lottoLine = new LottoLine(validNumbers);

            assertEquals(validNumbers, lottoLine.getAllNumbers());
        }
    }
}
