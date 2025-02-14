package org.gonza.javaplayground.framework.mapper;

import org.gonza.javaplayground.lotto.ui.LottoRequest;
import org.gonza.javaplayground.lotto.ui.LottoResponse;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public record RouteMap(
        Object clazz,
        RequestResolver<?> requestResolver,
        Method method,
        ResponseResolver responseResolver
) {
    public LottoResponse invokeHandler(LottoRequest request) throws InvocationTargetException, IllegalAccessException {
        Object req = resolveRequest(request);

        Object result = Objects.nonNull(req)
                ? method.invoke(clazz, req)
                : method.invoke(clazz);

        return resolveResponse(result);
    }

    private Object resolveRequest(LottoRequest request) {

        if (Objects.isNull(requestResolver)) {
            return null;
        }

        return requestResolver.resolve(request);
    }

    private LottoResponse resolveResponse(Object result) {
        if (Objects.isNull(responseResolver)) {
            return new LottoResponse(204, "");
        }

        return responseResolver.resolve(result);
    }
}
