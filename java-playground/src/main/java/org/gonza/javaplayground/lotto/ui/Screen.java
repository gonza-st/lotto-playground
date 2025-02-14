package org.gonza.javaplayground.lotto.ui;

import org.gonza.javaplayground.lotto.controller.Option;

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
    }

    public LottoRequest showSelections() {
        printer.println("What would you like to do?");
        printer.println("1. Purchase");
        printer.println("2. Match Numbers");

        Integer selectedType = scanner.nextInt();
        Option option = Option.fromCode(selectedType);

        switch (option) {
            case PURCHASE:
                return new LottoRequest(option);
            case MATCH:
                String inputNumbers = scanner.nextLine();
                return new LottoRequest(option, inputNumbers);
            default:
                throw new IllegalArgumentException("Invalid endpoint type");
        }
    }
}
