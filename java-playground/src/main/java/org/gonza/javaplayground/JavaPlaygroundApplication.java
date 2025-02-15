package org.gonza.javaplayground;

import org.gonza.javaplayground.core.*;
import org.gonza.javaplayground.view.Printer;
import org.gonza.javaplayground.view.Reader;

import java.math.BigDecimal;
import java.util.List;

public class JavaPlaygroundApplication {

    public static void main(String[] args) {
        Reader reader = new Reader();
        Printer printer = new Printer();
        LottoMachine lottoMachine = new LottoMachine(new LottoNumberGenerator());

        try {
            // 구매 금액 입력
            printer.printPurchaseAmountRequest();
            BigDecimal purchaseAmount = reader.readPurchaseAmount();
            Money money = new Money(purchaseAmount);

            // 로또 티켓 생성
            LottoTicket ticket = lottoMachine.generateLottoTicket(money);

            // 구매 정보 출력
            printer.printPurchasedTicketCount(ticket.lottoNumbers().size());
            printer.printLottoNumbers(ticket.lottoNumbers());

            // 당첨 번호 입력
            printer.printWinningNumberRequest();
            List<Integer> winningNumberList = reader.readWinningNumbers();

            // 당첨 통계 생성 및 출력
            LottoNumber winningNumber = new LottoNumber(winningNumberList);
            WinningStatistics statistics = new WinningStatistics(ticket.lottoNumbers(), winningNumber);
            printer.printStatistics(statistics);

        } catch (IllegalArgumentException e) {
            printer.printError(e.getMessage());
        } finally {
            reader.close();
        }
    }
}
