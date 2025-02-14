package org.gonza.javaplayground.lotto.service.lotto;

import java.util.List;

public class NumberGeneratorStub implements NumberGenerator {
    @Override
    public List<Integer> generate(Integer min, Integer max, Integer size) {
        return List.of(1, 2, 3, 4, 5, 6);
    }
}
