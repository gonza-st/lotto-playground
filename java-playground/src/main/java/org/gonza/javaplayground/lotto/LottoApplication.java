package org.gonza.javaplayground.lotto;

import org.gonza.javaplayground.framework.BenefitProgram;
import org.gonza.javaplayground.lotto.mapper.RouteMap;
import org.gonza.javaplayground.lotto.mapper.RouteMapper;
import org.gonza.javaplayground.lotto.ui.LottoRequest;
import org.gonza.javaplayground.lotto.ui.LottoResponse;
import org.gonza.javaplayground.lotto.ui.Screen;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

public class LottoApplication implements BenefitProgram {

    private RouteMapper routeMapper;

    public LottoApplication(RouteMapper routeMapper) {
        this.routeMapper = routeMapper;
    }

    @Override
    public void run(BufferedReader in, PrintWriter out) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Screen screen = new Screen(in, out);
        screen.showWelcomeMsg();

        while (true) {
            LottoRequest request = screen.showSelections();
            RouteMap routeMap = routeMapper.getRouteMap(request.getOption());

            LottoResponse response = routeMap.invokeHandler(request);
            screen.showResult(response);
        }
    }
}