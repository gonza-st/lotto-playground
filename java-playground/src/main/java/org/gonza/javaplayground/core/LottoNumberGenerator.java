package org.gonza.javaplayground.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumberGenerator {
    public LottoNumber generateLottoNumber() {
        List<Integer> lottoNumbers = new ArrayList<>();
        for (int i = LottoNumber.MIN_NUMBER; i <= LottoNumber.MAX_NUMBER; i++) {
            lottoNumbers.add(i);
        }
        Collections.shuffle(lottoNumbers);

        return new LottoNumber(lottoNumbers.subList(0, 6));
    }
}
