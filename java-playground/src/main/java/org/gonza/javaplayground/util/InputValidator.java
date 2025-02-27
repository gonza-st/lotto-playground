package org.gonza.javaplayground.util;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputValidator {
    public BigDecimal validateAndConvertPurchaseAmount(String input) {
        try {
            BigDecimal amount = new BigDecimal(input);
            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("구매 금액은 0보다 커야 합니다.");
            }
            return amount;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자 형식으로 입력해주세요.");
        }
    }

    public List<Integer> validateAndConvertWinningNumbers(String input) {
        try {
            String[] numbers = input.split(", ");
            List<Integer> winningNumbers = Arrays.stream(numbers)
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            validateWinningNumbers(winningNumbers);

            return winningNumbers;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("올바른 숫자 형식으로 입력해주세요.");
        }
    }

    private void validateWinningNumbers(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("6개의 당첨 번호를 입력해주세요.");
        }

        if (numbers.stream().distinct().count() != 6) {
            throw new IllegalArgumentException("당첨 번호는 중복될 수 없습니다.");
        }

        numbers.forEach(number -> {
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException("로또 번호는 1부터 45 사이의 숫자여야 합니다.");
            }
        });
    }

    public int validateBonusNumber(String bonusNumber) {
        int number = Integer.parseInt(bonusNumber);
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("보너스 번호는 1부터 45 사이의 숫자여야 합니다.");
        }

        return number;
    }
}
