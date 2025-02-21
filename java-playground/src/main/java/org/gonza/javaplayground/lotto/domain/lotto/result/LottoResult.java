package org.gonza.javaplayground.lotto.domain.lotto.result;

public interface LottoResult<T> {
    String lottoId();
    T result();
}
