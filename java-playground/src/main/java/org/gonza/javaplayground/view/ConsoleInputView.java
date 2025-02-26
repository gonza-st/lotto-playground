package org.gonza.javaplayground.view;

import java.util.Scanner;

public class ConsoleInputView implements LottoInputView {
    private final Scanner scanner;

    public ConsoleInputView() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String read() {
        return scanner.nextLine();
    }

    @Override
    public void close() {
        scanner.close();
    }
}
