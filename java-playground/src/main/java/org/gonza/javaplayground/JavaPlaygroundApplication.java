package org.gonza.javaplayground;

import org.gonza.javaplayground.framework.BenefitProgram;
import org.gonza.javaplayground.framework.Government;
import org.gonza.javaplayground.framework.server.ConsoleServer;
import org.gonza.javaplayground.framework.server.ServerProperties;
import org.gonza.javaplayground.lotto.LottoApplication;

import java.io.IOException;

public class JavaPlaygroundApplication {
    private static final Integer PORT = 3001;
    private static final Integer POOL_SIZE = 10;

    public static void main(String[] args) throws IOException {
        System.out.println("Java Application Started");
        BenefitProgram lotto = new LottoApplication();

        ServerProperties properties = new ServerProperties(PORT, POOL_SIZE);
        ConsoleServer console = new ConsoleServer(properties);

        Government government = new Government(console, lotto);
        government.work();
    }
}
