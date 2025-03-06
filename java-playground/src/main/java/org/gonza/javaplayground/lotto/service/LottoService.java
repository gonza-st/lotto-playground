package org.gonza.javaplayground.lotto.service;

import org.gonza.javaplayground.lotto.domain.lotto.lotto.Lotto;
import org.gonza.javaplayground.lotto.domain.lotto.lotto.LottoFactory;
import org.gonza.javaplayground.lotto.domain.lotto.lotto.LottoLine;
import org.gonza.javaplayground.lotto.domain.lotto.lotto.LottoNumber;
import org.gonza.javaplayground.lotto.domain.lotto.result.LottoResultList;
import org.gonza.javaplayground.lotto.domain.payment.Cash;
import org.gonza.javaplayground.lotto.domain.report.Payment;
import org.gonza.javaplayground.lotto.domain.report.Report;
import org.gonza.javaplayground.lotto.domain.report.ReportFactory;

import java.util.List;

public class LottoService {
    private final LottoFactory lottoFactory;
    private final ReportFactory reportFactory;

    public LottoService(LottoFactory lottoFactory, ReportFactory reportFactory) {
        this.lottoFactory = lottoFactory;
        this.reportFactory = reportFactory;
    }

    public Lotto issue(
            Integer amount,
            List<List<Integer>> manualNumbers
    ) {
        Cash cash = Cash.of(amount);
        return lottoFactory.createLotto(cash, manualNumbers);
    }

    public Report report(
            Integer amount,
            Lotto lotto,
            List<Integer> rawMatchingNumbers,
            Integer rawBonusNumber
    ) {
        Payment payment = Cash.of(amount);
        LottoLine matchingNumbers = lottoFactory.createLottoLine(rawMatchingNumbers);
        LottoNumber bonusNumber = lottoFactory.createLottoNumber(rawBonusNumber);
        LottoResultList matchedNumbers = lotto.match(matchingNumbers, bonusNumber);

        return reportFactory.printReport(payment, matchedNumbers);
    }
}
