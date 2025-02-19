package org.gonza.javaplayground.lotto.controller;

import org.gonza.javaplayground.lotto.controller.request.MatchReq;
import org.gonza.javaplayground.lotto.controller.request.PurchaseReq;
import org.gonza.javaplayground.lotto.controller.response.PurchaseRes;
import org.gonza.javaplayground.framework.mapper.Controller;
import org.gonza.javaplayground.lotto.domain.lotto.Lotto;
import org.gonza.javaplayground.lotto.domain.lotto.LottoFactory;
import org.gonza.javaplayground.lotto.domain.price.Coin;
import org.gonza.javaplayground.lotto.domain.price.MoneyExchanger;
import org.gonza.javaplayground.lotto.domain.price.Purchase;

public class LottoKiosk implements Controller {
    private final LottoFactory lottoFactory;
    private final MoneyExchanger moneyExchanger;
    private final Storage usb;

    public LottoKiosk(LottoFactory lottoFactory, MoneyExchanger moneyExchanger, Storage usb) {
        this.lottoFactory = lottoFactory;
        this.moneyExchanger = moneyExchanger;
        this.usb = usb;
    }

    public PurchaseRes handlePurchase(PurchaseReq req) {
        Purchase purchase = new Purchase(req.payment());
        Coin coin = moneyExchanger.exchangeMoney(purchase);
        Lotto lotto = lottoFactory.createLotto(coin);
        usb.save(lotto);

        return PurchaseRes.of(coin.count(), lotto.getAllLottoNumbers());
    }

    public void handleMatchNumbers(MatchReq req) {
          System.out.println("You got a match number!");
    }

    public void handleInvalidRequest() {
    }
}
