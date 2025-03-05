package org.gonza.javaplayground.lotto.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.gonza.javaplayground.lotto.LottoSupport;
import org.junit.jupiter.api.Test;

class LottoTest {

    @Test
    void Lotto는_생성시_ISSUED_다() {
        Lotto lotto = Lotto.of(LottoSupport.LOTTO_NUMBERS_OF_123456);

        assertTrue(lotto.isIssued());
    }

    @Test
    void matchedCount가_3_이상이면_WON_이다() {
		LottoNumbers matchNumbers = LottoNumbers.of(List.of(1, 2, 3, 7, 8, 9));
        Lotto lotto = Lotto.of(LottoSupport.LOTTO_NUMBERS_OF_123456);

        lotto.verify(matchNumbers, LottoSupport.BONUS_NUMBER_OF_45);

        assertTrue(lotto.isWon());
    }

    @Test
    void matchedCount가_3_미만이면_LOST_이다() {
		LottoNumbers matchNumbers = LottoNumbers.of(List.of(1, 2, 10, 7, 8, 9));
        Lotto lotto = Lotto.of(LottoSupport.LOTTO_NUMBERS_OF_123456);

        lotto.verify(matchNumbers, LottoSupport.BONUS_NUMBER_OF_45);

        assertTrue(lotto.isLost());
    }

    @Test
    void matchedCount가_3_이면_RANK는_FIFTH_이다() {
		LottoNumbers matchNumbers = LottoNumbers.of(List.of(1, 2, 3, 7, 8, 9));
        Lotto lotto = Lotto.of(LottoSupport.LOTTO_NUMBERS_OF_123456);

        lotto.verify(matchNumbers, LottoSupport.BONUS_NUMBER_OF_45);

        assertEquals(Rank.FIFTH, lotto.getRank());
    }

    @Test
    void matchedCount가_4_이면_RANK는_FOURTH_이다() {
		LottoNumbers matchNumbers = LottoNumbers.of(List.of(1, 2, 3, 4, 8, 9));
        Lotto lotto = Lotto.of(LottoSupport.LOTTO_NUMBERS_OF_123456);

        lotto.verify(matchNumbers, LottoSupport.BONUS_NUMBER_OF_45);

        assertEquals(Rank.FOURTH, lotto.getRank());
    }

    @Test
    void matchedCount가_5_이면_RANK는_THIRD_이다() {
		LottoNumbers matchNumbers = LottoNumbers.of(List.of(1, 2, 3, 4, 5, 9));
        Lotto lotto = Lotto.of(LottoSupport.LOTTO_NUMBERS_OF_123456);

        lotto.verify(matchNumbers, LottoSupport.BONUS_NUMBER_OF_45);

        assertEquals(Rank.THIRD, lotto.getRank());
    }

    @Test
    void matchedCount가_5_이면서_BONUS가_일치하면_RANK는_SECOND_이다() {
		LottoNumbers matchNumbers = LottoNumbers.of(List.of(1, 2, 3, 4, 5, 9));
		Lotto lotto = Lotto.of(LottoSupport.LOTTO_NUMBERS_OF_123456);

        lotto.verify(matchNumbers, LottoSupport.BONUS_NUMBER_OF_6);

        assertTrue(lotto.isWon());
    }

    @Test
    void matchedCount가_6_이면_RANK는_FIRST_이다() {
		LottoNumbers matchNumbers = LottoSupport.LOTTO_NUMBERS_OF_123456;
        Lotto lotto = Lotto.of(LottoSupport.LOTTO_NUMBERS_OF_123456);

        lotto.verify(matchNumbers, LottoSupport.BONUS_NUMBER_OF_45);

        assertEquals(Rank.FIRST, lotto.getRank());
    }

    @Test
    void matchedCount가_0_이면_RANK는_MISS_이다() {
		LottoNumbers matchNumbers = LottoNumbers.of(List.of(10, 11, 12, 7, 8, 9));
        Lotto lotto = Lotto.of(LottoSupport.LOTTO_NUMBERS_OF_123456);

        lotto.verify(matchNumbers, LottoSupport.BONUS_NUMBER_OF_45);

        assertEquals(Rank.MISS, lotto.getRank());
    }

    @Test
    void 로또_타입은_수동일_수_있다() {
        Lotto lotto = Lotto.manualOf(LottoSupport.LOTTO_NUMBERS_OF_123456);

        assertTrue(lotto.isManual());
    }

    @Test
    void 로또_타입은_자동일_수_있다() {
        Lotto lottoOf = Lotto.of(LottoSupport.LOTTO_NUMBERS_OF_123456);
        Lotto lottoAutomatic = Lotto.automaticOf(LottoSupport.LOTTO_NUMBERS_OF_123456);

        assertTrue(lottoOf.isAutomatic());
        assertTrue(lottoAutomatic.isAutomatic());
    }
}