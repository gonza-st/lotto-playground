package org.gonza.javaplayground.view;

import org.gonza.javaplayground.util.InputValidator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class ConsoleInputView implements LottoInputView {
    private final Scanner scanner;
    private final InputValidator validator;

    public ConsoleInputView(InputValidator validator) {
        this.scanner = new Scanner(System.in);
        this.validator = validator;
    }

    @Override
    public BigDecimal readPurchaseAmount() {
        String input = scanner.nextLine();
        return validator.validateAndConvertPurchaseAmount(input);
    }

    @Override
    public List<Integer> readWinningNumbers() {
        String input = scanner.nextLine();
        return validator.validateAndConvertWinningNumbers(input);
    }

    @Override
    public void close() {
        scanner.close();
    }
}
