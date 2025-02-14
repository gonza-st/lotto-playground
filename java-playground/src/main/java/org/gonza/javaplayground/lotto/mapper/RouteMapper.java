package org.gonza.javaplayground.lotto.mapper;

import org.gonza.javaplayground.lotto.controller.LottoKiosk;
import org.gonza.javaplayground.lotto.controller.Option;
import org.gonza.javaplayground.lotto.controller.request.MatchReq;
import org.gonza.javaplayground.lotto.controller.request.PurchaseReq;
import org.gonza.javaplayground.lotto.controller.resolver.*;

import java.util.Map;

import static java.util.Map.entry;

public class RouteMapper {
    private final Map<Option, RouteMap> optionHandlerMap;

    public RouteMapper(LottoKiosk kiosk) throws NoSuchMethodException {
        this.optionHandlerMap = mapHandlerToEndpoint(kiosk);
    }

    private Map<Option, RouteMap> mapHandlerToEndpoint(LottoKiosk kiosk) throws NoSuchMethodException {
        return Map.ofEntries(
                entry(
                        Option.PURCHASE,
                        new RouteMap(
                                kiosk,
                                new PurchaseRequestRequestResolver(),
                                kiosk.getClass().getMethod("handlePurchase", PurchaseReq.class),
                                new PurchaseResponseResolver()
                        )
                ),
                entry(
                        Option.MATCH,
                        new RouteMap(
                                kiosk,
                                new MatchRequestRequestResolver(),
                                kiosk.getClass().getMethod("handleMatchNumbers", MatchReq.class),
                                new MatchResponseResolver()
                        )
                ),
                entry(
                        Option.ERROR,
                        new RouteMap(
                                kiosk,
                                null,
                                kiosk.getClass().getMethod("handleInvalidRequest"),
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
