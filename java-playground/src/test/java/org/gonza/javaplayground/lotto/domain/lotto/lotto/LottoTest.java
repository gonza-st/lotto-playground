package org.gonza.javaplayground.lotto.domain.lotto.lotto;

import org.gonza.javaplayground.lotto.domain.lotto.result.LottoLineResult;
import org.gonza.javaplayground.lotto.domain.lotto.result.LottoResultList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class LottoTest {
    private List<LottoLine> givenNumbers;
    private Lotto sut;

    @BeforeEach
    public void setUp() {
        List<List<LottoNumber>> rawNumbers = List.of(
                Stream.of(1, 2, 3, 4, 5, 6).map(LottoNumber::new).toList(),
                Stream.of(11, 12, 13, 14, 15, 16).map(LottoNumber::new).toList()
        );

        this.givenNumbers = rawNumbers.stream()
                .map(LottoLine::new)
                .toList();

        this.sut = new Lotto(givenNumbers);
    }

    @Test
    public void should_have_id() {
        assertNotNull(this.sut.getId());
    }

    @Test
    public void should_return_matched_numbers_for_all_lines() {
        List<LottoNumber> matchingNumber = Stream.of(1, 2, 13, 14, 25, 33)
                .map(LottoNumber::new)
                .toList();

        LottoLine matchingLine = new LottoLine(matchingNumber);
        LottoNumber bonus = new LottoNumber(7);

        LottoResultList result = sut.match(matchingLine, bonus);

        List<LottoLineResult> resultMap =
                List.of(
                        new LottoLineResult(List.of(new LottoNumber(1), new LottoNumber(2))),
                        new LottoLineResult(List.of(new LottoNumber(13), new LottoNumber(14)))
                );

        assertEquals(resultMap, result.result());
    }

    @Test
    public void should_return_total_lines() {
        Integer count = sut.countLottoLines();
        assertEquals(givenNumbers.size(), count);
    }
}
