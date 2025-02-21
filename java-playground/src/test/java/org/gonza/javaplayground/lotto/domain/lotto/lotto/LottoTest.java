package org.gonza.javaplayground.lotto.domain.lotto.lotto;

import org.gonza.javaplayground.lotto.domain.lotto.result.LottoLineResult;
import org.gonza.javaplayground.lotto.domain.lotto.result.LottoResultList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class LottoTest {
    private List<List<Integer>> givenNumbers;
    private Lotto sut;

    @BeforeEach
    public void setUp() {
        this.givenNumbers = List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(11, 12, 13, 14, 15, 16)
        );

        this.sut = new Lotto(givenNumbers);
    }

    @Test
    public void should_have_id() {
        assertNotNull(this.sut.getId());
    }

    @Test
    public void should_throw_if_null_is_provided_in_constructor() {
        assertThrows(IllegalArgumentException.class, () -> new Lotto(null));
    }

    @Test
    public void should_throw_if_empty_list_is_provided_in_constructor() {
        assertThrows(IllegalArgumentException.class, () -> new Lotto(List.of()));
    }

    @Test
    public void should_return_matched_numbers_for_all_lines() {
        List<Integer> matchingNumber = List.of(1, 2, 13, 14, 25, 33);
        LottoLine matchingLine = new LottoLine(matchingNumber);

        LottoResultList result = sut.match(matchingLine);

        Map<Integer, List<LottoLineResult>> resultMap = Map.of(
                2, List.of(new LottoLineResult(List.of(1, 2)), new LottoLineResult(List.of(13, 14)))
        );
        assertEquals(resultMap, result.result());
    }

    @Test
    public void should_return_total_lines() {
        Integer count = sut.countLottoLines();
        assertEquals(givenNumbers.size(), count);
    }
}
