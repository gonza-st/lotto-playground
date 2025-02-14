package org.gonza.javaplayground.lotto.ui;

import org.gonza.javaplayground.framework.mapper.Option;

public class LottoRequestTestFixtures {
    public static LottoRequest createRequestWithNotNumberBody() {
        return new LottoRequest(Option.PURCHASE, "it is string");
    }

    public static LottoRequest createRequestWithNumberBody() {
        return new LottoRequest(Option.PURCHASE, "1000");
    }

    public static LottoRequest createRequestWithBooleanBody() {
        return new LottoRequest(Option.PURCHASE, Boolean.TRUE);
    }

}
