package org.gonza.javaplayground.lotto.ui;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SimpleScreenReader {
    private static final String DELIMITER = ",";

    private final Scanner scanner;
    private final PrintWriter printer;

    public SimpleScreenReader(BufferedReader in, PrintWriter out) {
        this.scanner = new Scanner(in);
        this.printer = out;
    }

    public List<Integer> enterMatchingNumbers() {
        printer.println("==============================");
        printer.println("당첨 번호 입력해주세요.");
        printer.println("==============================");
        String rawMatchingNumbers = scanStringInput();
        List<Integer> manualNumber = parseInputNumbers(rawMatchingNumbers);
        return manualNumber;
    }

    public Integer enterBonusNumber() {
        printer.println("==============================");
        printer.println("보너스 볼을 입력해주세요.");
        printer.println("==============================");
        String rawBonusNumber = scanStringInput();
        return Integer.parseInt(rawBonusNumber);
    }

    public Integer purchaseAmount() {
        printer.println("==============================");
        printer.println("구입 금액을 입력해주세요.");
        printer.println("==============================");

        String rawAmount = scanStringInput();
        return Integer.parseInt(rawAmount);
    }

    private List<Integer> parseInputNumbers(String body) {
        String[] split = body.split(DELIMITER);

        return Arrays.stream(split)
                .map(String::trim)
                .map(Integer::valueOf)
                .toList();
    }


    private String scanStringInput() {
        try {
            return scanner.nextLine();
        } catch (NumberFormatException e) {
            printer.println("==============================");
            printer.println("Please enter again");
            printer.println("==============================");

            return scanStringInput();
        }
    }
}
