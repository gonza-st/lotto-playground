package org.gonza.javaplayground.lotto;

import org.gonza.javaplayground.framework.Application;
import org.gonza.javaplayground.framework.mapper.Option;
import org.gonza.javaplayground.framework.mapper.RouteMap;
import org.gonza.javaplayground.framework.mapper.RouteMapper;
import org.gonza.javaplayground.lotto.ui.LottoRequest;
import org.gonza.javaplayground.lotto.ui.LottoResponse;
import org.gonza.javaplayground.lotto.ui.Screen;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LottoApplication implements Application {
    private List<Option> programFlow = List.of(Option.PURCHASE, Option.MATCH);
    private RouteMapper routeMapper;

    public LottoApplication(RouteMapper routeMapper) {
        this.routeMapper = routeMapper;
    }

    @Override
    public void run(BufferedReader in, PrintWriter out) {
        Queue<Option> flowQueue = new LinkedList<>(programFlow);

        Screen screen = new Screen(in, out);
        screen.showWelcomeMsg();

        while (!flowQueue.isEmpty()) {
            Option option = flowQueue.poll();
            LottoRequest request = screen.showSelections(option);

            RouteMap controllerMethod = routeMapper.getMethod(request.getOption());
            LottoResponse response = controllerMethod.invoke(request);

            screen.showResult(response);
        }
    }
}