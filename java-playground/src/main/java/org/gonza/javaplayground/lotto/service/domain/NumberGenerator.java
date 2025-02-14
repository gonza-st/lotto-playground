package org.gonza.javaplayground.lotto.service.domain;

import java.util.List;

public interface NumberGenerator {
    List<Integer> generate(Integer size, Integer min, Integer max);
}
