package org.gonza.javaplayground.lotto.controller.request.resolver;

import org.gonza.javaplayground.lotto.controller.request.MatchRequest;
import org.gonza.javaplayground.framework.mapper.RequestResolver;
import org.gonza.javaplayground.lotto.ui.LottoRequest;

import java.util.Arrays;
import java.util.List;

public class MatchRequestResolver implements RequestResolver<MatchRequest> {
    private static final String DELIMITER = ",";

    @Override
    public MatchRequest resolve(LottoRequest lottoRequest) {
        List<Integer> numbers = lottoRequest.getBody()
                .map(this::castToString)
                .map(this::parseInputNumbers)
                .orElseThrow();


        return new MatchRequest(numbers);
    }

    private List<Integer> parseInputNumbers(String body) {
        String[] split = body.split(DELIMITER);

        return Arrays.stream(split)
                .map(String::trim)
                .map(Integer::valueOf)
                .toList();
    }

    private String castToString(Object body) {
        try {
            return (String) body;
        } catch (ClassCastException e) {
            System.out.println("Cast Error: >> " + e);
            throw e;
        }
    }
}
