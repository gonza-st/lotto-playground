package org.gonza.javaplayground;

import org.gonza.javaplayground.framework.Application;
import org.gonza.javaplayground.framework.Summer;
import org.gonza.javaplayground.framework.server.ConsoleServer;
import org.gonza.javaplayground.framework.server.ServerProperties;
import org.gonza.javaplayground.lotto.LottoApplication;
import org.gonza.javaplayground.lotto.controller.LottoKiosk;
import org.gonza.javaplayground.framework.mapper.RouteMapper;

import java.io.IOException;

public class JavaPlaygroundApplication {
    private static final Integer PORT = 3001;
    private static final Integer POOL_SIZE = 10;

    public static void main(String[] args) throws IOException, NoSuchMethodException {
        System.out.println("Java Application Started");

        LottoKiosk lottoKiosk = new LottoKiosk();
        RouteMapper routeMapper = new RouteMapper(lottoKiosk);
        Application lotto = new LottoApplication(routeMapper);

        ServerProperties properties = new ServerProperties(PORT, POOL_SIZE);
        ConsoleServer console = new ConsoleServer(properties);

        Summer summer = new Summer(console, lotto);
        summer.work();
    }
}
