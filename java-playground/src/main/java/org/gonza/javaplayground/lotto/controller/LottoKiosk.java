package org.gonza.javaplayground.lotto.controller;

import org.gonza.javaplayground.lotto.controller.request.MatchReq;
import org.gonza.javaplayground.lotto.controller.request.PurchaseReq;

public class LottoKiosk {

    public String handlePurchase(PurchaseReq req) {
        return "You purchased a lotto.";
    }

    public void handleMatchNumbers(MatchReq req) {
        System.out.println("You got a match number!");
    }

    public void handleInvalidRequest() {}
}
