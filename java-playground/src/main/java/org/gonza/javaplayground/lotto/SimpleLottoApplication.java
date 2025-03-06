package org.gonza.javaplayground.lotto;

import org.gonza.javaplayground.framework.Application;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimpleLottoApplication implements Application {
    private Scanner scanner;
    private PrintWriter printer;

    @Override
    public void run(BufferedReader in, PrintWriter out) {
        this.scanner = new Scanner(in);
        this.printer = out;

        printer.println("==============================");
        printer.println("구입 금액을 입력해주세요.");
        printer.println("==============================");

        String rawAmount = scanStringInput();


        printer.println("==============================");
        printer.println("수동으로 구매할 로또 수를 입력해 주세요.");
        printer.println("==============================");

        String rawManualCount = scanStringInput();
        Integer manualCount = Integer.parseInt(rawManualCount);

        List<String> manualNumbers = new ArrayList<>();
        if (manualCount > 0) {
            printer.println("==============================");
            printer.println("수동으로 구매할 로또 수를 입력해 주세요.");
            printer.println("==============================");

            for (int i = 0; i < manualCount; i++) {
                manualNumbers.add(scanStringInput());
            }
        }

        printer.println("==============================");
        printer.println("구매 내역");
        printer.println("==============================");


        printer.println("==============================");
        printer.println("당첨 번호 입력해주세요.");
        printer.println("==============================");
        String matchingNumbers = scanStringInput();

        printer.println("==============================");
        printer.println("보너스 볼을 입력해주세요.");
        printer.println("==============================");
        String bonusNumber = scanStringInput();


        printer.println("==============================");
        printer.println("당첨 통계");
        printer.println("==============================");
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
