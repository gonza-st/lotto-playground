package org.gonza.javaplayground.lotto.controller;

import org.gonza.javaplayground.lotto.controller.request.MatchReq;
import org.gonza.javaplayground.lotto.controller.request.PurchaseReq;
import org.gonza.javaplayground.lotto.controller.response.PurchaseRes;
import org.gonza.javaplayground.framework.mapper.Controller;
import org.gonza.javaplayground.lotto.domain.lotto.Lotto;
import org.gonza.javaplayground.lotto.domain.lotto.LottoFactory;
import org.gonza.javaplayground.lotto.domain.price.PriceCalculator;

public class LottoKiosk implements Controller {
    private final LottoFactory lottoFactory;
    private final PriceCalculator priceCalculator;

    public LottoKiosk(LottoFactory lottoFactory, PriceCalculator priceCalculator) {
        this.lottoFactory = lottoFactory;
        this.priceCalculator = priceCalculator;
    }

    public PurchaseRes handlePurchase(PurchaseReq req) {
        Integer count = priceCalculator.getAvailableAmount(req.payment());
        Lotto lotto = lottoFactory.createLotto(count);

        return PurchaseRes.of(count, lotto.getAllLottoNumbers());
    }

    public void handleMatchNumbers(MatchReq req) {
        System.out.println("You got a match number!");
    }

    public void handleInvalidRequest() {}
}
