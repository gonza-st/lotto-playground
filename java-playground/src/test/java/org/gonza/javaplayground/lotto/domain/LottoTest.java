package org.gonza.javaplayground.lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LottoTest {

    @Test
    void Lotto는_생성시_ISSUED_다() {
        LottoNumbers lottoNumbers = LottoNumbers.of(List.of(1, 2, 3, 4, 5, 6));

        Lotto lotto = createLotto(lottoNumbers);

        assertTrue(lotto.isIssued());
    }

    @Test
    void matchedCount가_3_이상이면_WON_이다() {
        LottoNumbers lottoNumbers = LottoNumbers.of(List.of(1, 2, 3, 4, 5, 6));
        LottoNumbers matchNumbers = LottoNumbers.of(List.of(1, 2, 3, 7, 8, 9));
        Lotto lotto = createLotto(lottoNumbers);

        lotto.verify(matchNumbers);

        assertTrue(lotto.isWon());
    }

    @Test
    void matchedCount가_3_미만이면_LOST_이다() {
        LottoNumbers lottoNumbers = LottoNumbers.of(List.of(1, 2, 3, 4, 5, 6));
        LottoNumbers matchNumbers = LottoNumbers.of(List.of(1, 2, 10, 7, 8, 9));
        Lotto lotto = createLotto(lottoNumbers);

        lotto.verify(matchNumbers);

        assertTrue(lotto.isLost());
    }

    @Test
    void matchedCount가_3_이면_RANK는_FIFTH_이다() {
        LottoNumbers lottoNumbers = LottoNumbers.of(List.of(1, 2, 3, 4, 5, 6));
        LottoNumbers matchNumbers = LottoNumbers.of(List.of(1, 2, 3, 7, 8, 9));
        Lotto lotto = createLotto(lottoNumbers);

        lotto.verify(matchNumbers);

        assertEquals(Rank.FIFTH, lotto.getRank());
    }

    @Test
    void matchedCount가_4_이면_RANK는_FOURTH_이다() {
        LottoNumbers lottoNumbers = LottoNumbers.of(List.of(1, 2, 3, 4, 5, 6));
        LottoNumbers matchNumbers = LottoNumbers.of(List.of(1, 2, 3, 4, 8, 9));
        Lotto lotto = createLotto(lottoNumbers);

        lotto.verify(matchNumbers);

        assertEquals(Rank.FOURTH, lotto.getRank());
    }

    @Test
    void matchedCount가_5_이면_RANK는_THIRD_이다() {
        LottoNumbers lottoNumbers = LottoNumbers.of(List.of(1, 2, 3, 4, 5, 6));
        LottoNumbers matchNumbers = LottoNumbers.of(List.of(1, 2, 3, 4, 5, 9));
        Lotto lotto = createLotto(lottoNumbers);

        lotto.verify(matchNumbers);

        assertEquals(Rank.THIRD, lotto.getRank());
    }

    @Test
    void matchedCount가_5_이면서_BONUS가_일치하면_RANK는_SECOND_이다() {
        LottoNumbers lottoNumbers = LottoNumbers.of(List.of(1, 2, 3, 4, 5, 6));
        LottoNumbers matchNumbers = LottoNumbers.of(List.of(1, 2, 3, 4, 5, 9));
        LottoNumber bonusNumber = LottoNumber.of(6);
        Lotto lotto = createLotto(lottoNumbers);

        lotto.verify(matchNumbers, bonusNumber);

        assertTrue(lotto.isWon());
    }

    @Test
    void matchedCount가_6_이면_RANK는_FIRST_이다() {
        LottoNumbers lottoNumbers = LottoNumbers.of(List.of(1, 2, 3, 4, 5, 6));
        LottoNumbers matchNumbers = LottoNumbers.of(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto = createLotto(lottoNumbers);

        lotto.verify(matchNumbers);

        assertEquals(Rank.FIRST, lotto.getRank());
    }

    @Test
    void matchedCount가_0_이면_RANK는_MISS_이다() {
        LottoNumbers lottoNumbers = LottoNumbers.of(List.of(1, 2, 3, 4, 5, 6));
        LottoNumbers matchNumbers = LottoNumbers.of(List.of(10, 11, 12, 7, 8, 9));
        Lotto lotto = createLotto(lottoNumbers);

        lotto.verify(matchNumbers);

        assertEquals(Rank.MISS, lotto.getRank());
    }

    private static Lotto createLotto(LottoNumbers lottoNumbers) {
        return Lotto.of(lottoNumbers);
    }
}