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
import org.gonza.javaplayground.lotto.domain.receipt.MoneyExchanger;
import org.gonza.javaplayground.lotto.domain.coin.Purchase;

public class LottoKiosk implements Controller {
    private final LottoFactory lottoFactory;
    private final CoinFactory coinFactory;
    private final MoneyExchanger moneyExchanger;
    private final Storage usb;

    public LottoKiosk(LottoFactory lottoFactory, CoinFactory coinFactory, MoneyExchanger moneyExchanger, Storage usb) {
        this.lottoFactory = lottoFactory;
        this.coinFactory = coinFactory;
        this.moneyExchanger = moneyExchanger;
        this.usb = usb;
    }

    public PurchaseRes handlePurchase(PurchaseReq req) {
        Purchase purchase = new Purchase(req.payment());
        Coin coin = coinFactory.issueCoin(purchase);
        Lotto lotto = lottoFactory.createLotto(coin);
        usb.save(lotto);

        return PurchaseRes.of(coin.count(), lotto.getAllLottoNumbers());
    }

    public MatchRes handleMatchNumbers(MatchReq req) {
        LottoLine winningLine = LottoLine.of(req.numbers());

        Lotto lastLotto = usb.readLast();
        LottoResult matchedNumbers = lastLotto.match(winningLine);

        System.out.println("You got a match number!");
        return new MatchRes(1.1, "1");
    }

    public void handleInvalidRequest() {
    }
}
