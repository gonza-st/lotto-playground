package org.gonza.javaplayground.lotto.ui;

import org.gonza.javaplayground.framework.mapper.Option;

public class LottoRequestTestFixtures {
    public static LottoRequest createRequestWithNotNumberBody() {
        return new LottoRequest(Option.PURCHASE, "it is string");
    }

    public static LottoRequest createRequestWithIntegerBody() {
        return new LottoRequest(Option.PURCHASE, 1000);
    }

    public static LottoRequest createRequestWithBooleanBody() {
        return new LottoRequest(Option.PURCHASE, Boolean.TRUE);
    }

    public static LottoRequest createMatchRequestWithValidBody() {
        return new LottoRequest(Option.MATCH, "1,2,3,4,5,6");
    }

    public static LottoRequest createMatchRequestWithIntegerBody() {
        return new LottoRequest(Option.MATCH, 1);
    }

    public static LottoRequest createMatchRequestWithEmptyBody() {
        return new LottoRequest(Option.MATCH);
    }

    public static LottoRequest createMatchRequestWithNotNumberValueBody() {
        return new LottoRequest(Option.MATCH, "1,2,3,4,5,a");
    }
}
