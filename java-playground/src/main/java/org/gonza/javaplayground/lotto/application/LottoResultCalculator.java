package org.gonza.javaplayground.lotto.application;

import org.gonza.javaplayground.lotto.domain.LottoResult;
import org.gonza.javaplayground.lotto.domain.Lottos;
import org.gonza.javaplayground.lotto.domain.Price;

public class LottoResultCalculator {

	private LottoResultCalculator() {}

	public static LottoResult calculateResult(Lottos lottos, Price investedAmount) {
		LottoResult result = LottoResult.of(lottos, investedAmount);
		result.count();
		return result;
	}
}
