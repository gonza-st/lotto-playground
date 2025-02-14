package org.gonza.javaplayground.lotto;

import org.gonza.javaplayground.framework.Application;
import org.gonza.javaplayground.framework.mapper.RouteMap;
import org.gonza.javaplayground.framework.mapper.RouteMapper;
import org.gonza.javaplayground.lotto.ui.LottoRequest;
import org.gonza.javaplayground.lotto.ui.LottoResponse;
import org.gonza.javaplayground.lotto.ui.Screen;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

public class LottoApplication implements Application {

    private RouteMapper routeMapper;

    public LottoApplication(RouteMapper routeMapper) {
        this.routeMapper = routeMapper;
    }

    @Override
    public void run(BufferedReader in, PrintWriter out) throws InvocationTargetException, IllegalAccessException {
        Screen screen = new Screen(in, out);
        screen.showWelcomeMsg();

        while (true) {
            LottoRequest request = screen.showSelections();

            RouteMap controllerMethod = routeMapper.getMethod(request.getOption());
            LottoResponse response = controllerMethod.invoke(request);

            screen.showResult(response);
        }
    }
}