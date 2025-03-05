package org.gonza.javaplayground.view;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public List<String> readMultipleLines(int lineCount) {
        List<String> inputs = new ArrayList<>();
        for (int i = 0; i < lineCount; i++) {
            inputs.add(read());
        }
        return inputs;
    }
}
