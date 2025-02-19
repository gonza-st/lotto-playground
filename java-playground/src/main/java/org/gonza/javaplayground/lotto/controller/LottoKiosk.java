package org.gonza.javaplayground.lotto.controller;

import org.gonza.javaplayground.lotto.controller.request.MatchReq;
import org.gonza.javaplayground.lotto.controller.request.PurchaseReq;
import org.gonza.javaplayground.lotto.controller.response.MatchRes;
import org.gonza.javaplayground.lotto.controller.response.PurchaseRes;
import org.gonza.javaplayground.framework.mapper.Controller;
import org.gonza.javaplayground.lotto.domain.coin.CoinFactory;
import org.gonza.javaplayground.lotto.domain.lotto.Lotto;
import org.gonza.javaplayground.lotto.domain.lotto.LottoFactory;
import org.gonza.javaplayground.lotto.domain.lotto.LottoLine;
import org.gonza.javaplayground.lotto.domain.lotto.LottoResult;
import org.gonza.javaplayground.lotto.domain.coin.Coin;
import org.gonza.javaplayground.lotto.domain.receipt.Receipt;
import org.gonza.javaplayground.lotto.domain.receipt.ReceiptFactory;
import org.gonza.javaplayground.lotto.domain.coin.Purchase;

public class LottoKiosk implements Controller {
    private final LottoFactory lottoFactory;
    private final CoinFactory coinFactory;
    private final ReceiptFactory receiptFactory;
    private final Storage usb;

    public LottoKiosk(LottoFactory lottoFactory, CoinFactory coinFactory, ReceiptFactory receiptFactory, Storage usb) {
        this.lottoFactory = lottoFactory;
        this.coinFactory = coinFactory;
        this.receiptFactory = receiptFactory;
        this.usb = usb;
    }

    public PurchaseRes handlePurchase(PurchaseReq req) {
        Purchase purchase = new Purchase(req.payment());

        Coin coin = coinFactory.issueCoin(purchase);
        Lotto lotto = lottoFactory.createLotto(coin);
        usb.savePurchaseHistory(purchase, lotto);

        return PurchaseRes.of(coin.count(), lotto.getAllLottoNumbers());
    }

    public MatchRes handleMatchNumbers(MatchReq req) {
        LottoLine winningLine = LottoLine.of(req.numbers());

        Lotto lastLotto = usb.findRecentLotto();
        Purchase lottoPurchase = usb.findLottoPurchase(lastLotto.getId());

        LottoResult matchedNumbers = lastLotto.match(winningLine);
        Receipt receipt = receiptFactory.printReceipt(lottoPurchase, matchedNumbers);

        return new MatchRes(receipt.getProfit(), receipt.getStatistics());
    }

    public void handleInvalidRequest() {
    }
}
