package org.gonza.javaplayground.core;

import java.util.ArrayList;
import java.util.List;

public record LottoNumber(List<Integer> lottoNumbers) {
    public static final int MAX_NUMBER = 45;
    public static final int MIN_NUMBER = 1;
    public static final int REQUIRE_SIZE_FOR_LOTTO_NUMBER = 6;

    public static LottoNumber createBonusLottoNumber(int bonusNumber) {
        validateNumberRange(bonusNumber);

        return new LottoNumber(List.of(bonusNumber));
    }

    public static List<LottoNumber> generateManualLottoNumbers(List<String> inputs) {
        List<LottoNumber> manualLottoNumbers = new ArrayList<>();

        for (String input : inputs) {
            List<Integer> numbers = parseInputToNumbers(input);
            validateLottoNumber(numbers);
            manualLottoNumbers.add(new LottoNumber(numbers));
        }

        return manualLottoNumbers;
    }

    public Ranking getRanking(LottoNumber winningNumber, LottoNumber bonusNumber) {
        int matchCount = getMatchCount(winningNumber);
        boolean hasBonusMatch = lottoNumbers.contains(bonusNumber.lottoNumbers().get(0));

        return Ranking.valueOf(matchCount, hasBonusMatch);
    }

    public int getMatchCount(LottoNumber winningNumber) {
        return (int) lottoNumbers.stream()
                .filter(number -> winningNumber.lottoNumbers().contains(number))
                .count();
    }

    private static List<Integer> parseInputToNumbers(String input) {
        String[] parts = input.split(",");
        List<Integer> numbers = new ArrayList<>();

        for (String part : parts) {
            numbers.add(Integer.parseInt(part.trim()));
        }

        return numbers;

    }

    private static void validateLottoNumber(List<Integer> numbers) {
        if (numbers.size() != REQUIRE_SIZE_FOR_LOTTO_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }

        if (numbers.stream().distinct().count() != 6) {
            throw new IllegalArgumentException("로또 번호에 중복된 숫자가 있습니다.");
        }

        for (int number : numbers) {
            validateNumberRange(number);
        }
    }

    private static void validateNumberRange(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }
}
