package org.gonza.javaplayground.view;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Reader {
    private final Scanner scanner;

    public Reader() {
        this.scanner = new Scanner(System.in);
    }

    public BigDecimal readPurchaseAmount() {
        try {
            return new BigDecimal(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자 형식으로 입력해주세요.");
        }
    }

    public List<Integer> readWinningNumbers() {
        String input = scanner.nextLine();
        try {
            String[] winningNumbers = input.split(", ");
            List<Integer> numbers = Arrays.stream(winningNumbers)
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            if (numbers.size() != 6) {
                throw new IllegalArgumentException("6개의 당첨 번호를 입력해주세요.");
            }

            return numbers;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("올바른 숫자 형식으로 입력해주세요.");
        }
    }

    public void close() {
        scanner.close();
    }
}
