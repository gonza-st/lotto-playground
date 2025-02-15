package org.gonza.javaplayground.lotto.controller.request;

import java.util.List;

public record MatchReq(
        List<String> numbers
) {
}
