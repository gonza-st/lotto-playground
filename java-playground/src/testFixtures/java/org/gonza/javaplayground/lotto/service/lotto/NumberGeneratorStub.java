package org.gonza.javaplayground.lotto.service.lotto;

import java.util.List;

public class NumberGeneratorStub implements NumberGenerator {
    public static List<Integer> FIXED_LIST = List.of(1, 2, 3, 4, 5, 6);

    @Override
    public List<Integer> generate(Integer min, Integer max, Integer size) {
        return FIXED_LIST;
    }
}
