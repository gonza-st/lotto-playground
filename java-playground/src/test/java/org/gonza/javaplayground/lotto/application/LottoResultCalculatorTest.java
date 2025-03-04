package org.gonza.javaplayground.lotto.application;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.gonza.javaplayground.lotto.domain.LottoNumber;
import org.gonza.javaplayground.lotto.domain.LottoNumbers;
import org.gonza.javaplayground.lotto.domain.LottoResult;
import org.gonza.javaplayground.lotto.domain.Lottos;
import org.gonza.javaplayground.lotto.domain.Rank;
import org.gonza.javaplayground.lotto.domain.Price;
import org.junit.jupiter.api.Test;

class LottoResultCalculatorTest {

	@Test
	void Lottos와_Price로_LottoResult를_생성한다() {
		List<LottoNumbers> lottoNumbersList = List.of(
			LottoNumbers.of(Arrays.asList(1, 2, 3, 4, 5, 6))
		);
		Lottos lottos = Lottos.of(lottoNumbersList);
		Price price = Price.of(1000L);

		LottoResult result = LottoResultCalculator.calculateResult(lottos, price);

		assertThat(result).isNotNull();
	}

	@Test
	void 생성된_LottoResult에는_등수별_당첨_횟수가_집계되어_있다() {
		List<LottoNumbers> lottoNumbersList = new ArrayList<>();
		lottoNumbersList.add(LottoNumbers.of(Arrays.asList(1, 2, 3, 4, 5, 6)));
		lottoNumbersList.add(LottoNumbers.of(Arrays.asList(1, 2, 3, 4, 5, 7)));
		Lottos lottos = Lottos.of(lottoNumbersList);

		LottoNumbers winningNumbers = LottoNumbers.of(Arrays.asList(1, 2, 3, 4, 5, 6));
		LottoNumber bonusNumber = LottoNumber.of(7);
		lottos.verify(winningNumbers, bonusNumber);

		Price price = Price.of(2000L);

		LottoResult result = LottoResultCalculator.calculateResult(lottos, price);

		assertThat(result.getRankCount().get(Rank.FIRST)).isEqualTo(1);
		assertThat(result.getRankCount().get(Rank.THIRD)).isEqualTo(1);
	}

}