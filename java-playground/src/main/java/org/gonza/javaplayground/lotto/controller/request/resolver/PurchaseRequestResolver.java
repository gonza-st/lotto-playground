package org.gonza.javaplayground.lotto.controller.request.resolver;

import org.gonza.javaplayground.lotto.controller.request.PurchaseReq;
import org.gonza.javaplayground.framework.mapper.RequestResolver;
import org.gonza.javaplayground.lotto.ui.LottoRequest;

public class PurchaseRequestResolver implements RequestResolver<PurchaseReq> {
    @Override
    public PurchaseReq resolve(LottoRequest lottoRequest) {
        Integer amount = lottoRequest.getBody()
                .map(this::castToString)
                .map(this::parseStringToInteger)
                .orElseThrow();

        PurchaseReq req = new PurchaseReq(amount);
        return req;
    }

    private String castToString(Object body) {
        try {
            return (String) body;
        } catch (ClassCastException e) {
            throw e;
        }
    }

    private Integer parseStringToInteger(String value) {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            throw e;
        }
    }
}
