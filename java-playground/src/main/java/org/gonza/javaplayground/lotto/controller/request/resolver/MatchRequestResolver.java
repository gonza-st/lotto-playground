package org.gonza.javaplayground.lotto.controller.request.resolver;

import org.gonza.javaplayground.lotto.controller.request.MatchReq;
import org.gonza.javaplayground.framework.mapper.RequestResolver;
import org.gonza.javaplayground.lotto.domain.lotto.LottoLine;
import org.gonza.javaplayground.lotto.ui.LottoRequest;

import java.util.Arrays;
import java.util.List;

public class MatchRequestResolver implements RequestResolver<MatchReq> {
    private static final String DELIMITER = ",";
    private static final Integer SIZE = LottoLine.SIZE;
    private static final Integer MAX_NUMBER = LottoLine.MAX_NUMBER;
    private static final Integer MIN_NUMBER = LottoLine.MIN_NUMBER;

    @Override
    public MatchReq resolve(LottoRequest lottoRequest) {
        List<Integer> numbers = lottoRequest.getBody()
                .map(this::castToString)
                .map(this::parseInputNumbers)
                .map(this::validateLength)
                .map(this::validateMinMax)
                .orElseThrow();


        return new MatchReq(numbers);
    }

    private List<Integer> validateMinMax(List<Integer> numbers) {
        List<Integer> sortedNumbers = numbers.stream()
                .sorted()
                .toList();

        if (sortedNumbers.getFirst() < MIN_NUMBER) {
            throw new IllegalArgumentException("Number must be greater than " + MIN_NUMBER);
        }

        if (sortedNumbers.getLast() > MAX_NUMBER) {
            throw new IllegalArgumentException("Number must be less than " + MAX_NUMBER);
        }

        return numbers;
    }

    private List<Integer> validateLength(List<Integer> numbers) {
        if (!SIZE.equals(numbers.size())) {
            throw new IllegalArgumentException("Number size must be equal to " + SIZE);
        }

        return numbers;
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
