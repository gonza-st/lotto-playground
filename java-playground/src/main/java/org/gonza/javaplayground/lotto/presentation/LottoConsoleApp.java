package org.gonza.javaplayground.lotto.presentation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.gonza.javaplayground.lotto.application.LottoIssuer;
import org.gonza.javaplayground.lotto.application.LottoResultCalculator;
import org.gonza.javaplayground.lotto.domain.Lotto;
import org.gonza.javaplayground.lotto.domain.LottoNumber;
import org.gonza.javaplayground.lotto.domain.LottoNumbers;
import org.gonza.javaplayground.lotto.domain.LottoResult;
import org.gonza.javaplayground.lotto.domain.Lottos;
import org.gonza.javaplayground.lotto.domain.Price;
import org.gonza.javaplayground.lotto.domain.Rank;

public class LottoConsoleApp {
	private final Scanner scanner = new Scanner(System.in);

	public void run() {
		try {
			Price purchaseAmount = inputPurchaseAmount();
			List<List<Integer>> manualNumbers = inputManualNumbers();

			Lottos lottos = LottoIssuer.issue(purchaseAmount, manualNumbers);
			printLottoIssued(lottos);

			printLottoNumbers(lottos);

			LottoNumbers winningNumbers = inputWinningNumbers();

			LottoNumber bonusNumber = inputBonusNumber();

			lottos.verify(winningNumbers, bonusNumber);

			LottoResult result = LottoResultCalculator.calculateResult(lottos, purchaseAmount);

			printResult(result);
		} catch (Exception e) {
			System.out.println("오류가 발생했습니다: " + e.getMessage());
		} finally {
			scanner.close();
		}
	}

	private Price inputPurchaseAmount() {
		System.out.println("구입금액을 입력해 주세요.");
		long amount = Long.parseLong(scanner.nextLine());
		return Price.of(amount);
	}

	private List<List<Integer>> inputManualNumbers() {
		System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
		int manualPages = Integer.parseInt(scanner.nextLine());

		System.out.println("수동으로 구매할 번호를 입력해 주세요.");
		List<List<Integer>> manualNumbers = new ArrayList<>();

		for (int i = 0; i < manualPages; i++) {
			String manualNumber = scanner.nextLine();

			List<Integer> numbers = Arrays.stream(manualNumber.split(","))
				.map(String::trim)
				.map(Integer::parseInt)
				.toList();

			manualNumbers.add(numbers);
		}

		return manualNumbers;
	}

	private void printLottoIssued(Lottos lottos) {
		int automaticSize = lottos.getAutomaticSize();
		int manuSize = lottos.getManualSize();

		System.out.printf("수동으로 %s장, 자동으로 %s장을 구매했습니다.\n",
			manuSize,
			automaticSize);
	}

	private void printLottoNumbers(Lottos lottos) {
		List<Lotto> allLottos = lottos.getAllLottos();
		for (Lotto lotto : allLottos) {
			System.out.println(lotto.getNumberValues());
		}
		System.out.println();
	}

	private LottoNumbers inputWinningNumbers() {
		System.out.println("당첨 번호를 입력해 주세요.");
		String input = scanner.nextLine();

		List<Integer> numbers = Arrays.stream(input.split(","))
			.map(String::trim)
			.map(Integer::parseInt)
			.collect(Collectors.toList());

		return LottoNumbers.of(numbers);
	}

	private LottoNumber inputBonusNumber() {
		System.out.println("보너스 볼을 입력해 주세요.");
		int bonusNumber = Integer.parseInt(scanner.nextLine());
		return LottoNumber.of(bonusNumber);
	}

	public void printResult(LottoResult result) {
		System.out.println("당첨 통계");
		System.out.println("---------");

		Map<Rank, Integer> rankCount = result.getRankCount();

		System.out.printf("3개 일치 (%,d원)- %d개\n",
			Rank.FIFTH.getWinningMoney(),
			rankCount.get(Rank.FIFTH));

		System.out.printf("4개 일치 (%,d원)- %d개\n",
			Rank.FOURTH.getWinningMoney(),
			rankCount.get(Rank.FOURTH));

		System.out.printf("5개 일치 (%,d원)- %d개\n",
			Rank.THIRD.getWinningMoney(),
			rankCount.get(Rank.THIRD));

		System.out.printf("5개 일치, 보너스 볼 일치(%,d원) - %d개\n",
			Rank.SECOND.getWinningMoney(),
			rankCount.get(Rank.SECOND));

		System.out.printf("6개 일치 (%,d원)- %d개\n",
			Rank.FIRST.getWinningMoney(),
			rankCount.get(Rank.FIRST));

		double returnRate = result.calculateReturnRate();
		System.out.printf("총 수익률은 %.2f입니다.\n(기준이 1이기 때문에 결과적으로 %s라는 의미임)\n",
			returnRate,
			returnRate < 1 ? "손해" : "이득");
	}

	public static void main(String[] args) {
		new LottoConsoleApp().run();
	}
}
