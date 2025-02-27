package org.gonza.javaplayground.lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LottoTest {

    @Test
    void Lotto는_생성시_ISSUED_다() {
        LottoNumber lottoNumber = LottoNumber.of(List.of(1, 2, 3, 4, 5, 6));

        Lotto lotto = createLotto(lottoNumber);

        assertTrue(lotto.isIssued());
    }

    @Test
    void matchedCount가_3_이상이면_WON_이다() {
        LottoNumber lottoNumber = LottoNumber.of(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber matchNumber = LottoNumber.of(List.of(1, 2, 3, 7, 8, 9));
        Lotto lotto = createLotto(lottoNumber);

        lotto.verify(matchNumber);

        assertTrue(lotto.isWon());
    }

    @Test
    void matchedCount가_3_미만이면_LOST_이다() {
        LottoNumber lottoNumber = LottoNumber.of(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber matchNumber = LottoNumber.of(List.of(1, 2, 10, 7, 8, 9));
        Lotto lotto = createLotto(lottoNumber);

        lotto.verify(matchNumber);

        assertTrue(lotto.isLost());
    }

    @Test
    void matchedCount가_3_이면_RANK는_FIFTH_이다() {
        LottoNumber lottoNumber = LottoNumber.of(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber matchNumber = LottoNumber.of(List.of(1, 2, 3, 7, 8, 9));
        Lotto lotto = createLotto(lottoNumber);

        lotto.verify(matchNumber);

        assertEquals(Rank.FIFTH, lotto.getRank());
    }

    @Test
    void matchedCount가_4_이면_RANK는_FOURTH_이다() {
        LottoNumber lottoNumber = LottoNumber.of(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber matchNumber = LottoNumber.of(List.of(1, 2, 3, 4, 8, 9));
        Lotto lotto = createLotto(lottoNumber);

        lotto.verify(matchNumber);

        assertEquals(Rank.FOURTH, lotto.getRank());
    }

    @Test
    void matchedCount가_5_이면_RANK는_THIRD_이다() {
        LottoNumber lottoNumber = LottoNumber.of(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber matchNumber = LottoNumber.of(List.of(1, 2, 3, 4, 5, 9));
        Lotto lotto = createLotto(lottoNumber);

        lotto.verify(matchNumber);

        assertEquals(Rank.THIRD, lotto.getRank());
    }

    @Test
    void matchedCount가_6_이면_RANK는_FIRST_이다() {
        LottoNumber lottoNumber = LottoNumber.of(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber matchNumber = LottoNumber.of(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto = createLotto(lottoNumber);

        lotto.verify(matchNumber);

        assertEquals(Rank.FIRST, lotto.getRank());
    }

    @Test
    void matchedCount가_0_이면_RANK는_MISS_이다() {
        LottoNumber lottoNumber = LottoNumber.of(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber matchNumber = LottoNumber.of(List.of(10, 11, 12, 7, 8, 9));
        Lotto lotto = createLotto(lottoNumber);

        lotto.verify(matchNumber);

        assertEquals(Rank.MISS, lotto.getRank());
    }

    private static Lotto createLotto(LottoNumber lottoNumber) {
        return Lotto.of(lottoNumber);
    }
}