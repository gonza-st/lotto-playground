package org.gonza.javaplayground.framework.mapper;

import org.gonza.javaplayground.lotto.ui.LottoResponse;

public abstract class ResponseResolver<T> {
     public LottoResponse resolve(T response) {
         try {
             Integer status = 200;
             String data = format(response);
             return new LottoResponse(status, data);
         } catch (Exception e) {
             Integer status = 500;
             return new LottoResponse(status, e.getMessage());
         }
     }

     protected abstract String format(T response);
}
