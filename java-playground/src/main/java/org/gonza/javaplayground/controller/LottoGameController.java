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
            Money purchaseAmount = getPurchaseAmount();
            int manualCount = getManualLottoCount();
            List<LottoNumber> manualLottoNumbers = getManualLottoNumbers(manualCount);
            LottoTicket ticket = lottoMachine.generateLottoTicket(purchaseAmount, manualLottoNumbers);
            printTicketInfo(ticket, manualCount);
            WinningStatistics statistics = getWinningStatistics(ticket, processWinningNumbers());
            outputView.printStatistics(statistics);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
        }
    }

    private Money getPurchaseAmount() {
        outputView.printPurchaseAmountRequest();
        BigDecimal amount = inputValidator.validateAndConvertPurchaseAmount(inputView.read());
        return new Money(amount);
    }

    private int getManualLottoCount() {
        outputView.printManualPurchaseCountRequest();
        int manualCount = inputValidator.inputNumber(inputView.read());
        return manualCount;
    }

    private List<LottoNumber> getManualLottoNumbers(int manualCount) {
        if (manualCount <= 0) {
            return List.of();
        }

        outputView.printManualNumbersRequest();
        List<String> inputs = inputView.readMultipleLines(manualCount);

        return LottoNumber.generateManualLottoNumbers(inputs);
    }

    private void printTicketInfo(LottoTicket ticket, int manualCount) {
        int autoCount = ticket.lottoNumbers().size() - manualCount;
        outputView.printPurchasedTicketSummary(manualCount, autoCount);
        outputView.printLottoNumbers(ticket.lottoNumbers());
    }

    private LottoNumber processWinningNumbers() {
        outputView.printWinningNumberRequest();
        List<Integer> winningNumbers = inputValidator.validateAndConvertWinningNumbers(inputView.read());
        return new LottoNumber(winningNumbers);
    }

    private WinningStatistics getWinningStatistics(LottoTicket ticket, LottoNumber winningNumber) {
        outputView.printBonusNumberRequest();
        int inputBonusNumber = inputValidator.inputNumber(inputView.read());
        LottoNumber bonusLottoNumber = LottoNumber.createBonusLottoNumber(inputBonusNumber);

        return new WinningStatistics(ticket.lottoNumbers(), winningNumber, bonusLottoNumber);
    }
}
