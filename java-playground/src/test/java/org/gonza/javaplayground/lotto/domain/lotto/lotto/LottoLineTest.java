package org.gonza.javaplayground.lotto.domain.lotto.lotto;

import org.gonza.javaplayground.lotto.domain.lotto.LottoProperties;
import org.gonza.javaplayground.lotto.domain.lotto.result.LottoLineResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottoLineTest {
    private static final LottoProperties properties = new LottoProperties(
            1000, 6, 1, 45
    );

    private LottoLine sut;

    @BeforeEach
    public void setUp() {
        List<LottoNumber> givenNumbers = Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::new).toList();

        this.sut = new LottoLine(givenNumbers);
    }

    @Nested
    class MatchTest {
        @Test
        public void should_return_all_numbers_when_every_number_matches() {
            List<LottoNumber> allMatchingNumbers = Stream.of(1, 2, 3, 4, 5, 6)
                    .map(LottoNumber::new).toList();

            LottoLine line = new LottoLine(allMatchingNumbers);

            LottoLineResult matchedNumber = sut.match(line);
            assertEquals(allMatchingNumbers, matchedNumber.getResult().stream().map(LottoNumber::new).toList());
        }

        @Test
        public void should_return_matched_number_list() {
            List<LottoNumber> someMatchingNumbers = Stream.of(1, 2, 3, 7, 8, 9)
                    .map(LottoNumber::new).toList();

            LottoLine line = new LottoLine(someMatchingNumbers);

            LottoLineResult matchedNumber = sut.match(line);
            assertEquals(List.of(1, 2, 3).stream().map(LottoNumber::new).toList(), matchedNumber.getResult().stream().map(LottoNumber::new).toList());
        }

        @Test
        public void should_return_empty_list_if_nothing_matches() {
            List<LottoNumber> nothingMatchingNumbers = Stream.of(7, 8, 9, 10, 11, 12)
                    .map(LottoNumber::new).toList();;

            LottoLine line = new LottoLine(nothingMatchingNumbers);

            LottoLineResult matchedNumber = sut.match(line);
            assertEquals(List.of(), matchedNumber.getResult());
        }
    }

    @Nested
    class CreationTest {
        @Test
        public void should_return_valid_lotto_line() {
            List<LottoNumber> validNumbers = Stream.of(1, 2, 3, 4, 5, 6)
                    .map(LottoNumber::new).collect(Collectors.toList());

            LottoLine lottoLine = new LottoLine(validNumbers);

            assertEquals(validNumbers, lottoLine.getAllNumbers().stream().map(LottoNumber::new).toList());
        }
    }
}
