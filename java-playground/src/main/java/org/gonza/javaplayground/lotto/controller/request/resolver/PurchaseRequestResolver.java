package org.gonza.javaplayground.lotto.controller.request.resolver;

import org.gonza.javaplayground.lotto.controller.request.PurchaseReq;
import org.gonza.javaplayground.framework.mapper.RequestResolver;
import org.gonza.javaplayground.lotto.ui.LottoRequest;

public class PurchaseRequestResolver implements RequestResolver<PurchaseReq> {
    @Override
    public PurchaseReq resolve(LottoRequest lottoRequest) {

        Object body = lottoRequest.getBody();
        PurchaseReq req = new PurchaseReq("1");

        return req;
    }
}
