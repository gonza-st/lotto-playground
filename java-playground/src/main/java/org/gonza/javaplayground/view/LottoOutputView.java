package org.gonza.javaplayground.view;

import org.gonza.javaplayground.core.LottoNumber;
import org.gonza.javaplayground.core.WinningStatistics;

import java.util.List;

public interface LottoOutputView {
    void printPurchaseAmountRequest();

    void printPurchasedTicketCount(int count);

    void printLottoNumbers(List<LottoNumber> numbers);

    void printWinningNumberRequest();

    void printStatistics(WinningStatistics statistics);

    void printError(String message);

}
