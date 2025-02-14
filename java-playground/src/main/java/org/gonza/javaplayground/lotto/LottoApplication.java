package org.gonza.javaplayground.lotto;

import org.gonza.javaplayground.framework.BenefitProgram;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class LottoApplication implements BenefitProgram {

    @Override
    public void run(BufferedReader in, PrintWriter out) {
        Scanner scanner = new Scanner(in);
        out.println("hello");
        String value = scanner.nextLine();
        out.println("what u entered: >> " + value);
    }
}
