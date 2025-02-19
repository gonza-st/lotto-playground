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
import org.gonza.javaplayground.lotto.domain.coin.CoinFactory;
import org.gonza.javaplayground.lotto.domain.lotto.LottoFactory;
import org.gonza.javaplayground.lotto.domain.lotto.NumberGenerator;
import org.gonza.javaplayground.lotto.domain.receipt.ReceiptFactory;
import org.gonza.javaplayground.lotto.domain.receipt.WinningPriceTable;
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
    private static final List<Integer> WINNING_PRICE_RANGE = List.of(0, 0, 0, 5000, 50000, 1500000, 2000000000);

    private static Application initializeApplication() throws NoSuchMethodException {
        WinningPriceTable winningPriceTable = new WinningPriceTable(WINNING_PRICE_RANGE);
        ReceiptFactory receiptFactory = new ReceiptFactory(winningPriceTable);

        CoinFactory coinFactory = new CoinFactory(PRICE);

        NumberGenerator numberGenerator = new RandomNumberGenerator();
        LottoFactory lottoFactory = new LottoFactory(numberGenerator);

        Storage usb = new USB();

        LottoKiosk lottoKiosk = new LottoKiosk(lottoFactory, coinFactory, receiptFactory, usb);
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
