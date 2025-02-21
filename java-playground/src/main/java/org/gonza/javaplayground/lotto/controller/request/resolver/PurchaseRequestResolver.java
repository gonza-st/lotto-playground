package org.gonza.javaplayground.lotto.controller.request.resolver;

import org.gonza.javaplayground.lotto.controller.request.PurchaseRequest;
import org.gonza.javaplayground.framework.mapper.RequestResolver;
import org.gonza.javaplayground.lotto.ui.LottoRequest;

public class PurchaseRequestResolver implements RequestResolver<PurchaseRequest> {
    @Override
    public PurchaseRequest resolve(LottoRequest lottoRequest) {
        Integer amount = lottoRequest.getBody()
                .map(this::castToInteger)
                .orElseThrow();

        PurchaseRequest req = new PurchaseRequest(amount);
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
