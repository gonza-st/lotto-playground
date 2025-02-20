package org.gonza.javaplayground.lotto.ui.adapter;

import org.gonza.javaplayground.lotto.controller.response.PurchaseRes;
import org.gonza.javaplayground.framework.mapper.ResponseResolver;

public class PurchaseUiAdapter extends ResponseResolver<PurchaseRes> {

    @Override
    protected String format(PurchaseRes response) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(response.getLottoNumbers().size()).append("개를 구매했습니다.\n");

        response.getLottoNumbers().forEach((lottoNumber) -> {
            stringBuilder.append(lottoNumber).append("\n");
        });

        return stringBuilder.toString().trim();
    }
}
