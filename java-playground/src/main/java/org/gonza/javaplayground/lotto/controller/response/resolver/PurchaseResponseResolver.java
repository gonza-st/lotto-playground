package org.gonza.javaplayground.lotto.controller.response.resolver;

import org.gonza.javaplayground.lotto.controller.response.PurchaseRes;
import org.gonza.javaplayground.framework.mapper.ResponseResolver;
import org.gonza.javaplayground.lotto.ui.LottoResponse;

public class PurchaseResponseResolver implements ResponseResolver<PurchaseRes> {
    @Override
    public LottoResponse resolve(PurchaseRes response) {

        Integer status = 200;
        String data = format(response);

        return new LottoResponse(status, data);
    }

    private String format(PurchaseRes response) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(response.getLottoNumbers().size()).append("개를 구매했습니다.\n");

        response.getLottoNumbers().forEach((lottoNumber) -> {
            stringBuilder.append(lottoNumber).append("\n");
        });

        return stringBuilder.toString().trim();
    }
}
