package org.gonza.javaplayground.framework.mapper;

import org.gonza.javaplayground.lotto.controller.request.MatchReq;
import org.gonza.javaplayground.lotto.controller.request.PurchaseReq;
import org.gonza.javaplayground.lotto.controller.response.resolver.ErrorResponseResolver;
import org.gonza.javaplayground.lotto.controller.request.resolver.MatchRequestResolver;
import org.gonza.javaplayground.lotto.controller.request.resolver.PurchaseRequestResolver;
import org.gonza.javaplayground.lotto.controller.response.resolver.MatchResponseResolver;
import org.gonza.javaplayground.lotto.controller.response.resolver.PurchaseResponseResolver;

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
                                controller.getClass().getMethod("handlePurchase", PurchaseReq.class),
                                new PurchaseResponseResolver()
                        )
                ),
                entry(
                        Option.MATCH,
                        new RouteMap(
                                controller,
                                new MatchRequestResolver(),
                                controller.getClass().getMethod("handleMatchNumbers", MatchReq.class),
                                new MatchResponseResolver()
                        )
                ),
                entry(
                        Option.ERROR,
                        new RouteMap(
                                controller,
                                null,
                                controller.getClass().getMethod("handleInvalidRequest"),
                                new ErrorResponseResolver()
                        )
                )
        );
    }

    public RouteMap getRouteMap(Option option) {
        RouteMap routeMap = optionHandlerMap.get(option);

        if (routeMap == null) {
            return optionHandlerMap.get(Option.PURCHASE);
        }

        return routeMap;
    }
}
