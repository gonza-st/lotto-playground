package org.gonza.javaplayground;

import org.gonza.javaplayground.framework.BenefitProgram;
import org.gonza.javaplayground.framework.Government;
import org.gonza.javaplayground.lotto.LottoApplication;

import java.io.IOException;

public class JavaPlaygroundApplication {

    public static void main(String[] args) throws IOException {
        System.out.println("Java Application Started");
        BenefitProgram lotto = new LottoApplication();

        Government government = new Government(lotto);
        government.work();
    }
}
