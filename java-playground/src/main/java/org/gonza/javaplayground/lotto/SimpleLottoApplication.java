package org.gonza.javaplayground.lotto;

import org.gonza.javaplayground.framework.Application;
import org.gonza.javaplayground.lotto.domain.lotto.lotto.Lotto;
import org.gonza.javaplayground.lotto.domain.report.Report;
import org.gonza.javaplayground.lotto.service.LottoService;
import org.gonza.javaplayground.lotto.ui.SimpleScreenPrinter;
import org.gonza.javaplayground.lotto.ui.SimpleScreenReader;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;


public class SimpleLottoApplication implements Application {
    private final LottoService lottoService;

    public SimpleLottoApplication(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    @Override
    public void run(BufferedReader in, PrintWriter out) {
        SimpleScreenReader screen = new SimpleScreenReader(in, out);
        SimpleScreenPrinter printer = new SimpleScreenPrinter(out);

        // 구매
        Integer buyingAmount = screen.purchaseAmount();
        List<List<Integer>> manualNumbers = screen.purchaseManual();
        Lotto lotto = lottoService.issue(buyingAmount, manualNumbers);
        printer.showBuyingNumbers(lotto.getAllLottoNumbers());

        // 당첨번호 비교
        List<Integer> matchingNumbers = screen.enterMatchingNumbers();
        Integer bonusNumber = screen.enterBonusNumber();
        Report report = lottoService.report(buyingAmount, lotto, matchingNumbers, bonusNumber);
        printer.showReport(report.getProfit(), report.getStatistics());
    }
}
