package org.gonza.javaplayground.lotto.ui;

import org.gonza.javaplayground.framework.mapper.Option;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Screen {
    private final Scanner scanner;
    private final PrintWriter printer;

    public Screen(BufferedReader in, PrintWriter out) {
        this.scanner = new Scanner(in);
        this.printer = out;
    }

    public void showWelcomeMsg() {
        printer.println("Welcome to Lotto!");
        printer.println();
    }

    public void showResult(LottoResponse response) {
        printer.println(response.data());
    }

    public LottoRequest showSelections(Option option) {
        switch (option) {
            case PURCHASE:
                return handlePurchase();
            case MATCH:
                return handleMatchNumbers();
            default:
                throw new IllegalArgumentException("Invalid endpoint type");
        }
    }

    private LottoRequest handlePurchase() {
        printer.println("==============================");
        printer.println("How much do u wanna buy? (min price is 1000)");
        printer.println("==============================");

        try {
            String amount = scanner.nextLine();
            Integer parsedAmount = Integer.parseInt(amount);
            return new LottoRequest(Option.PURCHASE, parsedAmount);
        } catch (NumberFormatException e) {
            return handlePurchase();
        }
    }

    private LottoRequest handleMatchNumbers() {
        printer.println("==============================");
        printer.println("Enter your lotto line");
        printer.println("==============================");

        try {
            String inputNumbers = scanner.nextLine();
            return new LottoRequest(Option.MATCH, inputNumbers);
        } catch (NumberFormatException e) {
            return handleMatchNumbers();
        }
    }
}
