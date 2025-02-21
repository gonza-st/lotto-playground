package org.gonza.javaplayground.framework.mapper;

import org.gonza.javaplayground.lotto.controller.ErrorHandler;
import org.gonza.javaplayground.lotto.controller.request.MatchRequest;
import org.gonza.javaplayground.lotto.controller.request.PurchaseRequest;
import org.gonza.javaplayground.lotto.controller.request.resolver.MatchRequestResolver;
import org.gonza.javaplayground.lotto.controller.request.resolver.PurchaseRequestResolver;
import org.gonza.javaplayground.lotto.ui.adapter.MatchUiAdapter;
import org.gonza.javaplayground.lotto.ui.adapter.PurchaseUiAdapter;

import java.util.Map;

import static java.util.Map.entry;

public class RouteMapper {
    private final Map<Option, RouteMap> optionHandlerMap;

    public RouteMapper(Controller controller) throws NoSuchMethodException {
        this.optionHandlerMap = mapHandlerToEndpoint(controller);
    }

    private Map<Option, RouteMap> mapHandlerToEndpoint(Controller controller) throws NoSuchMethodException {
        return Map.ofEntries(
                entry(
                        Option.PURCHASE,
                        new RouteMap(
                                controller,
                                new PurchaseRequestResolver(),
                                controller.getClass().getMethod("handlePurchase", PurchaseRequest.class),
                                new PurchaseUiAdapter(),
                                new ErrorHandler()
                        )
                ),
                entry(
                        Option.MATCH,
                        new RouteMap(
                                controller,
                                new MatchRequestResolver(),
                                controller.getClass().getMethod("handleMatchNumbers", MatchRequest.class),
                                new MatchUiAdapter(),
                                new ErrorHandler()
                        )
                )
        );
    }

    public RouteMap getMethod(Option option) {
        RouteMap routeMap = optionHandlerMap.get(option);

        if (routeMap == null) {
            return optionHandlerMap.get(Option.PURCHASE);
        }

        return routeMap;
    }
}
