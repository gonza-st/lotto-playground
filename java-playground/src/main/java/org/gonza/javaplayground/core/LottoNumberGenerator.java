package org.gonza.javaplayground.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumberGenerator {

    public static final int MAX_NUMBER = 45;
    public static final int MIN_NUMBER = 1;

    public List<Integer> generateLottoNumber() {
        List<Integer> lottoNumbers = new ArrayList<>();
        for (int i = MIN_NUMBER; i <= MAX_NUMBER; i++) {
            lottoNumbers.add(i);
        }
        Collections.shuffle(lottoNumbers);

        return lottoNumbers.subList(0, 6);
    }
}
