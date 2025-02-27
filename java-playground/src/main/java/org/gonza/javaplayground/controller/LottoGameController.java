package org.gonza.javaplayground.controller;

import org.gonza.javaplayground.core.*;
import org.gonza.javaplayground.util.InputValidator;
import org.gonza.javaplayground.view.LottoInputView;
import org.gonza.javaplayground.view.LottoOutputView;

import java.math.BigDecimal;
import java.util.List;

public class LottoGameController {
    private final LottoInputView inputView;
    private final LottoOutputView outputView;
    private final LottoMachine lottoMachine;
    private final InputValidator inputValidator;

    public LottoGameController(
            LottoInputView inputView,
            LottoOutputView outputView,
            LottoMachine lottoMachine, InputValidator validator
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoMachine = lottoMachine;
        this.inputValidator = validator;
    }

    public void play() {
        try {
            LottoTicket ticket = purchaseLottoTicket();
            printTicketInfo(ticket);
            WinningStatistics statistics = getWinningStatistics(ticket, processWinningNumbers());
            outputView.printStatistics(statistics);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
        }
    }

    private LottoTicket purchaseLottoTicket() {
        outputView.printPurchaseAmountRequest();
        BigDecimal purchaseAmount = inputValidator.validateAndConvertPurchaseAmount(inputView.read());
        return lottoMachine.generateLottoTicket(new Money(purchaseAmount));
    }

    private void printTicketInfo(LottoTicket ticket) {
        outputView.printPurchasedTicketCount(ticket.lottoNumbers().size());
        outputView.printLottoNumbers(ticket.lottoNumbers());
    }

    private LottoNumber processWinningNumbers() {
        outputView.printWinningNumberRequest();
        List<Integer> winningNumbers = inputValidator.validateAndConvertWinningNumbers(inputView.read());

        return new LottoNumber(winningNumbers);
    }

    private WinningStatistics getWinningStatistics(LottoTicket ticket, LottoNumber winningNumber) {
        outputView.printBonusNumberRequest();
        int inputBonusNumber = inputValidator.validateBonusNumber(inputView.read());
        LottoNumber bonusLottoNumber = LottoNumber.createBonusLottoNumber(inputBonusNumber);

        return new WinningStatistics(ticket.lottoNumbers(), winningNumber, bonusLottoNumber);
    }
}
