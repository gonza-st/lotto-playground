package org.gonza.javaplayground.lotto.controller;

import org.gonza.javaplayground.lotto.controller.request.MatchRequest;
import org.gonza.javaplayground.lotto.controller.request.PurchaseRequest;
import org.gonza.javaplayground.lotto.controller.response.MatchResponse;
import org.gonza.javaplayground.lotto.controller.response.PurchaseResponse;
import org.gonza.javaplayground.framework.mapper.Controller;
import org.gonza.javaplayground.lotto.domain.lotto.lotto.Lotto;
import org.gonza.javaplayground.lotto.domain.lotto.lotto.LottoFactory;
import org.gonza.javaplayground.lotto.domain.lotto.lotto.LottoLine;
import org.gonza.javaplayground.lotto.domain.lotto.lotto.LottoNumber;
import org.gonza.javaplayground.lotto.domain.lotto.result.LottoResultList;
import org.gonza.javaplayground.lotto.domain.report.Payment;
import org.gonza.javaplayground.lotto.domain.report.Report;
import org.gonza.javaplayground.lotto.domain.report.ReportFactory;
import org.gonza.javaplayground.lotto.domain.payment.Cash;

public class LottoKiosk implements Controller {
    private final LottoFactory lottoFactory;
    private final ReportFactory reportFactory;
    private final Storage usb;

    public LottoKiosk(LottoFactory lottoFactory, ReportFactory reportFactory, Storage usb) {
        this.lottoFactory = lottoFactory;
        this.reportFactory = reportFactory;
        this.usb = usb;
    }

    public PurchaseResponse handlePurchase(PurchaseRequest request) {
        Cash cash = Cash.of(request.payment());

        Lotto lotto = lottoFactory.createLotto(cash);
        usb.savePurchaseHistory(cash, lotto);

        return new PurchaseResponse(lotto.getAllLottoNumbers());
    }

    public MatchResponse handleMatchNumbers(MatchRequest request) {
        LottoLine matchingNumbers = lottoFactory.createLottoLine(request.numbers());
        LottoNumber bonusNumber = lottoFactory.createLottoNumber(request.bonusNumber());

        Lotto latestLotto = usb.findRecentLotto();
        LottoResultList matchedNumbers = latestLotto.match(matchingNumbers, bonusNumber);

        Payment payment = usb.findLottoPayment(latestLotto.getId());
        Report report = reportFactory.printReport(payment, matchedNumbers);

        return new MatchResponse(
                report.getProfit(),
                report.getStatistics()
        );
    }
}
