package org.gonza.javaplayground.lotto;

import java.util.List;

import org.gonza.javaplayground.lotto.domain.LottoNumber;
import org.gonza.javaplayground.lotto.domain.LottoNumbers;

public class LottoSupport {
	public static final LottoNumbers LOTTO_NUMBERS_OF_123456 = LottoNumbers.of(List.of(1, 2, 3, 4, 5, 6));
	public static final LottoNumber BONUS_NUMBER_OF_6 = LottoNumber.of(6);
	public static final LottoNumber BONUS_NUMBER_OF_45 = LottoNumber.of(45);
}
