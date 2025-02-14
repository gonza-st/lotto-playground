package org.gonza.javaplayground.lotto.mapper;

import org.gonza.javaplayground.lotto.ui.LottoRequest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public record RouteMap(
        Object clazz,
        RequestResolver<?> requestResolver,
        Method method
) {
    public void invokeHandler(LottoRequest request) throws InvocationTargetException, IllegalAccessException {
        if (requestResolver != null) {
            withResolver(request);
        } else {
            withoutResolver();
        }
    }

    private void withResolver(LottoRequest request) throws InvocationTargetException, IllegalAccessException {
        Object req = requestResolver.resolve(request);
        Object result = method.invoke(clazz, req);
    }

    private void withoutResolver() throws InvocationTargetException, IllegalAccessException {
        method.invoke(clazz);
    }
}
