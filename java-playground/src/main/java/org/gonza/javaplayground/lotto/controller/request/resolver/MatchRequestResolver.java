package org.gonza.javaplayground.lotto.controller.request.resolver;

import org.gonza.javaplayground.lotto.controller.request.MatchRequest;
import org.gonza.javaplayground.framework.mapper.RequestResolver;
import org.gonza.javaplayground.lotto.ui.LottoRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MatchRequestResolver implements RequestResolver<MatchRequest> {
    private static final String DELIMITER = ",";
    private static final String MATCHING_NUMBER_KEY = "matchingNumbers";
    private static final String BONUS_NUMBER_KEY = "bonusNumber";

    @Override
    public MatchRequest resolve(LottoRequest lottoRequest) {
        Map<String, String> input = (Map<String, String>) lottoRequest.getBody().orElseThrow();

        String matchingNumberInput = input.getOrDefault(MATCHING_NUMBER_KEY, null);
        List<Integer> matchingNumbers = Optional.ofNullable(matchingNumberInput)
                .map(this::castToString)
                .map(this::parseInputNumbers)
                .orElseThrow();

        String bonusNumberInput = input.getOrDefault(BONUS_NUMBER_KEY, null);
        Integer bonusNumber = Optional.ofNullable(bonusNumberInput)
                .map(this::castToString)
                .map(Integer::valueOf)
                .orElseThrow();


        return new MatchRequest(matchingNumbers, bonusNumber);
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
