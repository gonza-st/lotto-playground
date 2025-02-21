package org.gonza.javaplayground.lotto.controller;

import org.gonza.javaplayground.lotto.controller.request.MatchRequest;
import org.gonza.javaplayground.lotto.controller.request.PurchaseRequest;
import org.gonza.javaplayground.lotto.controller.response.MatchResponse;
import org.gonza.javaplayground.lotto.controller.response.PurchaseResponse;
import org.gonza.javaplayground.framework.mapper.Controller;
import org.gonza.javaplayground.lotto.domain.lotto.Lotto;
import org.gonza.javaplayground.lotto.domain.lotto.LottoFactory;
import org.gonza.javaplayground.lotto.domain.lotto.LottoLine;
import org.gonza.javaplayground.lotto.domain.lotto.LottoResult;
import org.gonza.javaplayground.lotto.domain.receipt.Payment;
import org.gonza.javaplayground.lotto.domain.receipt.Receipt;
import org.gonza.javaplayground.lotto.domain.receipt.ReceiptFactory;
import org.gonza.javaplayground.lotto.domain.payment.Cash;

public class LottoKiosk implements Controller {
    private final LottoFactory lottoFactory;
    private final ReceiptFactory receiptFactory;
    private final Storage usb;

    public LottoKiosk(LottoFactory lottoFactory, ReceiptFactory receiptFactory, Storage usb) {
        this.lottoFactory = lottoFactory;
        this.receiptFactory = receiptFactory;
        this.usb = usb;
    }

    public PurchaseResponse handlePurchase(PurchaseRequest req) {
        Cash cash = Cash.of(req.payment());

        Lotto lotto = lottoFactory.createLotto(cash);
        usb.savePurchaseHistory(cash, lotto);

        return new PurchaseResponse(lotto.getAllLottoNumbers());
    }

    public MatchResponse handleMatchNumbers(MatchRequest req) {
        LottoLine winningLine = new LottoLine(req.numbers());

        Lotto latestLotto = usb.findRecentLotto();
        LottoResult matchedNumbers = latestLotto.match(winningLine);

        Payment payment = usb.findLottoPayment(latestLotto.getId());
        Receipt receipt = receiptFactory.printReceipt(payment, matchedNumbers);

        return new MatchResponse(
                receipt.getLottoId(),
                receipt.getProfit(),
                receipt.getStatistics()
        );
    }
}
