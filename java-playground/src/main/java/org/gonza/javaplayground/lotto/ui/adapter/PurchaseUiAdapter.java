package org.gonza.javaplayground.lotto.ui.adapter;

import org.gonza.javaplayground.lotto.controller.response.PurchaseResponse;
import org.gonza.javaplayground.framework.mapper.ResponseResolver;

public class PurchaseUiAdapter extends ResponseResolver<PurchaseResponse> {

    @Override
    protected String format(PurchaseResponse response) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(response.lottoNumbers().size()).append("개를 구매했습니다.\n");

        response.lottoNumbers().forEach((lottoNumber) -> {
            stringBuilder.append(lottoNumber).append("\n");
        });

        return stringBuilder.toString().trim();
    }
}
