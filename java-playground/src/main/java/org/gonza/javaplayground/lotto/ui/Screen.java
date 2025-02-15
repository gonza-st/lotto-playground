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
        printer.println();
        printer.println(response.data());
        printer.println();
    }

    public LottoRequest showSelections() {
        showIntroduction();

        Integer selectedType = scanner.nextInt();
        Option option = Option.fromCode(selectedType);

        switch (option) {
            case PURCHASE:
                return handlePurchase();
            case MATCH:
                String inputNumbers = scanner.nextLine();
                return new LottoRequest(option, inputNumbers);
            default:
                throw new IllegalArgumentException("Invalid endpoint type");
        }
    }

    private void showIntroduction() {
        printer.println("==============================");
        printer.println("What would you like to do?");
        printer.println("1. Purchase");
        printer.println("2. Match Numbers");
        printer.println("==============================");
    }

    private LottoRequest handlePurchase() {
        printer.println("==============================");
        printer.println("How much do u wanna buy? (min price is 1000)");
        printer.println("==============================");

        Integer amount = scanner.nextInt();
        return new LottoRequest(Option.PURCHASE, amount);
    }

}
