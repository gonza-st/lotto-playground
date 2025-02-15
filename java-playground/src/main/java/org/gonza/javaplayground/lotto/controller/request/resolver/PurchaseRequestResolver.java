package org.gonza.javaplayground.lotto.controller.request.resolver;

import org.gonza.javaplayground.lotto.controller.request.PurchaseReq;
import org.gonza.javaplayground.framework.mapper.RequestResolver;
import org.gonza.javaplayground.lotto.ui.LottoRequest;

public class PurchaseRequestResolver implements RequestResolver<PurchaseReq> {
    @Override
    public PurchaseReq resolve(LottoRequest lottoRequest) {
        Integer amount = lottoRequest.getBody()
                .map(this::castToInteger)
                .orElseThrow();

        PurchaseReq req = new PurchaseReq(amount);
        return req;
    }

    private Integer castToInteger(Object body) {
        try {
            return (Integer) body;
        } catch (ClassCastException e) {
            System.out.println("Cast Error: >> " + e);
            throw e;
        }
    }
}
