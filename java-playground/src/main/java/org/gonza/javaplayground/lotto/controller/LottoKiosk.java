package org.gonza.javaplayground.lotto.controller;

import org.gonza.javaplayground.lotto.controller.request.MatchReq;
import org.gonza.javaplayground.lotto.controller.request.PurchaseReq;
import org.gonza.javaplayground.lotto.controller.response.MatchRes;
import org.gonza.javaplayground.lotto.controller.response.PurchaseRes;
import org.gonza.javaplayground.framework.mapper.Controller;
import org.gonza.javaplayground.lotto.domain.lotto.Lotto;
import org.gonza.javaplayground.lotto.domain.lotto.LottoFactory;
import org.gonza.javaplayground.lotto.domain.lotto.LottoLine;
import org.gonza.javaplayground.lotto.domain.lotto.LottoResult;
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

    public PurchaseRes handlePurchase(PurchaseReq req) {
        Cash cash = Cash.of(req.payment());

        Lotto lotto = lottoFactory.createLotto(cash);
        usb.savePurchaseHistory(cash, lotto);

        return new PurchaseRes(lotto.getAllLottoNumbers());
    }

    public MatchRes handleMatchNumbers(MatchReq req) {
        LottoLine winningLine = LottoLine.of(req.numbers());

        Lotto lastLotto = usb.findRecentLotto();
        Cash lottoCash = usb.findLottoPurchase(lastLotto.getId());

        LottoResult matchedNumbers = lastLotto.match(winningLine);
        Receipt receipt = receiptFactory.printReceipt(lottoCash, matchedNumbers);

        return new MatchRes(
                receipt.getLottoId(),
                receipt.getProfit(),
                receipt.getStatistics()
        );
    }
}
