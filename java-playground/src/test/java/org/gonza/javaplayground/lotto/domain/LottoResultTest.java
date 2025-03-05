package org.gonza.javaplayground.lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class LottoResultTest {

	@Test
	void 당첨된_로또가_없으면_수익률은_0이다() {
		Lottos lottos = createLottosWithNoWinnings();
		Price investedAmount = Price.of(5000L);

		LottoResult result = LottoResult.of(lottos, investedAmount);
		result.count();
		double returnRate = result.calculateReturnRate();

		assertThat(returnRate).isEqualTo(0.0);
	}

	@Test
	void _5등_당첨_로또가_있으면_수익률이_계산된다() {
		Lottos lottos = createLottosWithFifthRank();
		Price investedAmount = Price.of(1000L);

		LottoResult result = LottoResult.of(lottos, investedAmount);
		result.count();
		double returnRate = result.calculateReturnRate();

		assertThat(returnRate).isEqualTo(500.0);
	}

	@Test
	void 등수별_당첨_횟수가_정확히_집계된다() {
		Lottos lottos = createMixedRankLottos();
		Price investedAmount = Price.of(2000L);

		LottoResult result = LottoResult.of(lottos, investedAmount);
		result.count();
		Map<Rank, Integer> rankCount = result.getRankCount();

		assertThat(rankCount.get(Rank.FIFTH)).isEqualTo(1);
		assertThat(rankCount.get(Rank.FOURTH)).isEqualTo(1);
		assertThat(rankCount.get(Rank.THIRD)).isEqualTo(0);
		assertThat(rankCount.get(Rank.SECOND)).isEqualTo(0);
		assertThat(rankCount.get(Rank.FIRST)).isEqualTo(0);
	}

	@Test
	void 총_당첨금액이_계산된다() {
		Lottos lottos = createMixedRankLottos();
		Price investedAmount = Price.of(2000L);

		LottoResult result = LottoResult.of(lottos, investedAmount);
		result.count();
		long totalAmount = result.calculateTotalWinningAmount();

		assertThat(totalAmount).isEqualTo(55000L);
	}

	private Lottos createLottosWithNoWinnings() {
		List<LottoNumbers> lottoNumbers = List.of(LottoNumbers.of(Arrays.asList(1, 2, 3, 4, 5, 6)));
		Lottos lottos = Lottos.of(lottoNumbers);
		lottos.verify(LottoNumbers.of(Arrays.asList(10, 20, 30, 40, 42, 45)), LottoNumber.of(41));
		return lottos;
	}

	private Lottos createLottosWithFifthRank() {
		List<LottoNumbers> lottoNumbers = List.of(LottoNumbers.of(Arrays.asList(1, 2, 3, 4, 5, 6)));
		Lottos lotto = Lottos.of(lottoNumbers);
		lotto.verify(LottoNumbers.of(Arrays.asList(1, 2, 3, 10, 20, 30)), LottoNumber.of(40));
		return lotto;
	}

	private Lottos createMixedRankLottos() {
		List<LottoNumbers> lottoNumbers = List.of(
			LottoNumbers.of(Arrays.asList(1, 2, 3, 10, 20, 30)),
			LottoNumbers.of(Arrays.asList(1, 2, 3, 4, 20, 30)));

		Lottos lottos = Lottos.of(lottoNumbers);

		lottos.verify(LottoNumbers.of(Arrays.asList(1, 2, 3, 4, 5, 6)), LottoNumber.of(7));

		return lottos;
	}
}