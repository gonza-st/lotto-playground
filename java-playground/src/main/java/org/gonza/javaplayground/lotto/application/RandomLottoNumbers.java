package org.gonza.javaplayground.lotto.application;

import org.gonza.javaplayground.lotto.domain.LottoNumber;
import org.gonza.javaplayground.util.RandomNumber;

import java.util.List;

public class RandomLottoNumbers {

    public static final int SIZE = 6;
    public static final int START = 1;
    public static final int END = 45;

    public static LottoNumber generate() {
        List<Integer> numbers = RandomNumber.generate(SIZE, START, END);
        return LottoNumber.of(numbers);
    }
}
