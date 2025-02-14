package org.gonza.javaplayground.lotto.controller;

import org.gonza.javaplayground.lotto.controller.request.MatchReq;
import org.gonza.javaplayground.lotto.controller.request.PurchaseReq;
import org.gonza.javaplayground.lotto.controller.response.PurchaseRes;
import org.gonza.javaplayground.framework.mapper.Controller;

public class LottoKiosk implements Controller {

    public PurchaseRes handlePurchase(PurchaseReq req) {
        PurchaseRes res = new PurchaseRes("You purchased a lotto.");
        return res;
    }

    public void handleMatchNumbers(MatchReq req) {
        System.out.println("You got a match number!");
    }

    public void handleInvalidRequest() {}
}
