package org.gonza.javaplayground.lotto.controller.resolver;

import org.gonza.javaplayground.lotto.controller.request.MatchReq;
import org.gonza.javaplayground.lotto.mapper.RequestResolver;
import org.gonza.javaplayground.lotto.ui.LottoRequest;

public class MatchRequestRequestResolver implements RequestResolver<MatchReq> {
    @Override
    public MatchReq resolve(LottoRequest lottoRequest) {
        return null;
    }
}
