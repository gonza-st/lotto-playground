package org.gonza.javaplayground.view;

import org.gonza.javaplayground.core.LottoNumber;
import org.gonza.javaplayground.core.WinningStatistics;

import java.util.List;

public class ConsoleOutputView implements LottoOutputView {
    @Override
    public void printPurchaseAmountRequest() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    @Override
    public void printPurchasedTicketCount(int count) {
        System.out.println(count + "개를 구매했습니다.");
    }

    @Override
    public void printLottoNumbers(List<LottoNumber> numbers) {
        numbers.forEach(number -> System.out.println(number.lottoNumbers()));
    }

    @Override
    public void printWinningNumberRequest() {
        System.out.println("\n당첨 번호를 입력해 주세요.");
    }

    @Override
    public void printStatistics(WinningStatistics statistics) {
        System.out.println("\n" + statistics);
    }

    @Override
    public void printError(String message) {
        System.out.println("Error: " + message);
    }
}
