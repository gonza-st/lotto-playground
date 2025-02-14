package org.gonza.javaplayground.lotto.controller.response.resolver;

import org.gonza.javaplayground.lotto.controller.response.PurchaseRes;
import org.gonza.javaplayground.framework.mapper.ResponseResolver;
import org.gonza.javaplayground.lotto.ui.LottoResponse;

public class PurchaseResponseResolver implements ResponseResolver<PurchaseRes> {
    @Override
    public LottoResponse resolve(PurchaseRes response) {
        return new LottoResponse(200, response.data());
    }
}
