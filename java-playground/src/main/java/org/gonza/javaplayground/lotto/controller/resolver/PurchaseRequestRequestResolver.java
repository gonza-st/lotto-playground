package org.gonza.javaplayground.lotto.controller.resolver;

import org.gonza.javaplayground.lotto.controller.request.PurchaseReq;
import org.gonza.javaplayground.lotto.mapper.RequestResolver;
import org.gonza.javaplayground.lotto.ui.LottoRequest;

public class PurchaseRequestRequestResolver implements RequestResolver<PurchaseReq> {
    @Override
    public PurchaseReq resolve(LottoRequest lottoRequest) {

        Object body = lottoRequest.getBody();
        PurchaseReq req = new PurchaseReq("1");

        return req;
    }
}
