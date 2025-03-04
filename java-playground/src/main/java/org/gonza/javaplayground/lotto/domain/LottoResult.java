package org.gonza.javaplayground.lotto.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gonza.javaplayground.price.domain.Price;

public class LottoResult {
	private final List<Lotto> lottos;
	private final Price investedAmount;
	private final Map<Rank, Integer> rankCount;

	private LottoResult(List<Lotto> lottos, Price investedAmount) {
		this.lottos = lottos;
		this.investedAmount = investedAmount;
		this.rankCount = new HashMap<>();
		initRankCount();
	}

	public static LottoResult of(Lottos lottos, Price investedAmount) {
		return new LottoResult(lottos.getAllLottos(), investedAmount);
	}

	private void initRankCount() {
		for (Rank rank : Rank.values()) {
			rankCount.put(rank, 0);
		}
	}

	public void count() {
		List<Lotto> wonLottos = lottos.stream()
			.filter(Lotto::isWon)
			.toList();

		for (Lotto lotto : wonLottos) {
			Rank rank = lotto.getRank();
			rankCount.put(rank, rankCount.get(rank) + 1);
		}
	}

	public double calculateReturnRate() {
		long totalWinningAmount = calculateTotalWinningAmount();
		return calculateRate(totalWinningAmount);
	}

	public long calculateTotalWinningAmount() {
		return rankCount.entrySet().stream()
			.mapToLong(entry -> (long) entry.getKey().getWinningMoney() * entry.getValue())
			.sum();
	}

	private double calculateRate(long totalWinningAmount) {
		return (double) totalWinningAmount / investedAmount.getValue() * 100;
	}

	public Map<Rank, Integer> getRankCount() {
		return Collections.unmodifiableMap(rankCount);
	}
}
