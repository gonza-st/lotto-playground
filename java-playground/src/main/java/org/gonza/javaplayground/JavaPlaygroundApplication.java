package org.gonza.javaplayground;

import org.gonza.javaplayground.framework.Application;
import org.gonza.javaplayground.framework.Summer;
import org.gonza.javaplayground.framework.server.ConsoleServer;
import org.gonza.javaplayground.framework.server.Server;
import org.gonza.javaplayground.framework.server.ServerProperties;
import org.gonza.javaplayground.lotto.LottoApplication;
import org.gonza.javaplayground.lotto.controller.LottoKiosk;
import org.gonza.javaplayground.framework.mapper.RouteMapper;
import org.gonza.javaplayground.lotto.controller.Storage;
import org.gonza.javaplayground.lotto.domain.lotto.lotto.LottoFactory;
import org.gonza.javaplayground.lotto.domain.lotto.LottoProperties;
import org.gonza.javaplayground.lotto.domain.lotto.NumberGenerator;
import org.gonza.javaplayground.lotto.domain.lotto.lotto.LottoLineFactory;
import org.gonza.javaplayground.lotto.domain.lotto.lotto.LottoNumberFactory;
import org.gonza.javaplayground.lotto.domain.report.ReportFactory;
import org.gonza.javaplayground.lotto.domain.report.WinningPrizeTable;
import org.gonza.javaplayground.lotto.domain.utils.RandomNumberGenerator;
import org.gonza.javaplayground.lotto.repository.USB;

import java.io.IOException;
import java.util.List;

public class JavaPlaygroundApplication {

    public static void main(String[] args) throws IOException, NoSuchMethodException {
        System.out.println("Java Application Started");

        Application application = initializeApplication();
        Server server = initializeServer();

        Summer summer = new Summer(server, application);
        summer.work();
    }

    private static final Integer PRICE = 1000;
    private static final Integer SIZE = 6;
    private static final Integer MAX_NUMBER = 45;
    private static final Integer MIN_NUMBER = 1;
    private static final List<Integer> WINNING_PRIZE_RANGE = List.of(0, 0, 0, 5_000, 50_000, 1_500_000, 2_000_000_000);
    private static final Integer BONUS_PRIZE = 30_000_000;

    private static Application initializeApplication() throws NoSuchMethodException {
        WinningPrizeTable winningPrizeTable = new WinningPrizeTable(WINNING_PRIZE_RANGE, BONUS_PRIZE);
        ReportFactory reportFactory = new ReportFactory(winningPrizeTable);

        NumberGenerator numberGenerator = new RandomNumberGenerator();
        LottoProperties properties = new LottoProperties(PRICE, SIZE, MIN_NUMBER, MAX_NUMBER);

        LottoNumberFactory lottoNumberFactory = new LottoNumberFactory(properties, numberGenerator);
        LottoLineFactory lottoLineFactory = new LottoLineFactory(properties, lottoNumberFactory);
        LottoFactory lottoFactory = new LottoFactory(properties, lottoLineFactory, lottoNumberFactory);

        Storage usb = new USB();

        LottoKiosk lottoKiosk = new LottoKiosk(lottoFactory, reportFactory, usb);
        RouteMapper routeMapper = new RouteMapper(lottoKiosk);
        return new LottoApplication(routeMapper);
    }


    private static final Integer PORT = 3001;
    private static final Integer POOL_SIZE = 10;

    private static Server initializeServer() {
        ServerProperties properties = new ServerProperties(PORT, POOL_SIZE);
        return new ConsoleServer(properties);
    }
}
