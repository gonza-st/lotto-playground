package org.gonza.javaplayground.lotto.mapper;

import org.gonza.javaplayground.lotto.ui.LottoRequest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public record RouteMap(
        Object clazz,
        Resolver<?> resolver,
        Method method
) {
    public void invokeHandler(LottoRequest request) throws InvocationTargetException, IllegalAccessException {
        if (resolver != null) {
            withResolver(request);
        } else {
            withoutResolver();
        }
    }

    private void withResolver(LottoRequest request) throws InvocationTargetException, IllegalAccessException {
        Object req = resolver.resolve(request);
        method.invoke(clazz, req);
    }

    private void withoutResolver() throws InvocationTargetException, IllegalAccessException {
        method.invoke(clazz);
    }
}
