package org.gonza.javaplayground.lotto.domain.lotto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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

        this.sut = Lotto.of(givenNumbers);
    }

    @Test
    public void should_have_id() {
        assertNotNull(this.sut.getId());
    }

    @Test
    public void should_throw_if_null_is_provided_in_constructor() {
        assertThrows(IllegalArgumentException.class, () -> Lotto.of(null));
    }

    @Test
    public void should_throw_if_empty_list_is_provided_in_constructor() {
        assertThrows(IllegalArgumentException.class, () -> Lotto.of(List.of()));
    }

    @Test
    public void should_return_matched_numbers_for_all_lines() {
        List<Integer> matchingNumber = List.of(1, 2, 13, 14, 25, 33);
        LottoLine matchingLine = LottoLine.of(matchingNumber);

        LottoResult result = sut.match(matchingLine);

        List<List<Integer>> expected = List.of(List.of(1, 2), List.of(13, 14));
        assertEquals(expected, result.getResults());
    }

    @Test
    public void should_return_total_lines() {
        Integer count = sut.countLottoLines();
        assertEquals(givenNumbers.size(), count);
    }
}
