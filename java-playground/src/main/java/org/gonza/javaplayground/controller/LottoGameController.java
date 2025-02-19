package org.gonza.javaplayground.controller;

import org.gonza.javaplayground.core.*;
import org.gonza.javaplayground.view.LottoInputView;
import org.gonza.javaplayground.view.LottoOutputView;

import java.math.BigDecimal;
import java.util.List;

public class LottoGameController {
    private final LottoInputView inputView;
    private final LottoOutputView outputView;
    private final LottoMachine lottoMachine;

    public LottoGameController(
            LottoInputView inputView,
            LottoOutputView outputView,
            LottoMachine lottoMachine
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoMachine = lottoMachine;
    }

    public void play() {
        try {
            LottoTicket ticket = purchaseLottoTicket();
            printTicketInfo(ticket);
            WinningStatistics statistics = processWinningNumbers(ticket);
            outputView.printStatistics(statistics);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
        }
    }

    private LottoTicket purchaseLottoTicket() {
        outputView.printPurchaseAmountRequest();
        BigDecimal purchaseAmount = inputView.readPurchaseAmount();
        return lottoMachine.generateLottoTicket(new Money(purchaseAmount));
    }

    private void printTicketInfo(LottoTicket ticket) {
        outputView.printPurchasedTicketCount(ticket.lottoNumbers().size());
        outputView.printLottoNumbers(ticket.lottoNumbers());
    }

    private WinningStatistics processWinningNumbers(LottoTicket ticket) {
        outputView.printWinningNumberRequest();
        List<Integer> winningNumbers = inputView.readWinningNumbers();
        LottoNumber winningNumber = new LottoNumber(winningNumbers);
        return new WinningStatistics(ticket.lottoNumbers(), winningNumber);
    }
}
