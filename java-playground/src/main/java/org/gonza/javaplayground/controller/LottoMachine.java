package org.gonza.javaplayground.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.gonza.javaplayground.model.Lotto;
import org.gonza.javaplayground.model.LottoNumber;

public class LottoMachine {
    private static final int 로또_번호_개수 = 6;
    private final NumberGenerator 난수생성기;

    public LottoMachine() {
        this.난수생성기 = new RandomGeneratorDetail();
    }

    public LottoMachine(NumberGenerator 난수생성기) {
        this.난수생성기 = 난수생성기;
    }

    public List<Lotto> 로또_생성하기(int 구매_수량) {
        List<Lotto> 로또_목록 = new ArrayList<>();
        for (int i = 0; i < 구매_수량; i++) {
            Lotto 로또 = 단일_로또_생성하기();
            로또_목록.add(로또);
        }
        return 로또_목록;
    }

    private Lotto 단일_로또_생성하기() {
        List<LottoNumber> 로또_숫자 = new ArrayList<>();
        Set<Integer> 사용된_번호 = new HashSet<>();

        while (로또_숫자.size() < 로또_번호_개수) {
            LottoNumber 로또_번호 = 로또_번호_생성하기();
            int 번호_값 = 로또_번호.번호_값_가져오기();

            if (사용된_번호.add(번호_값)) {
                로또_숫자.add(로또_번호);
            }
        }

        return new Lotto(로또_숫자);
    }

    private LottoNumber 로또_번호_생성하기() {
        int 번호 = 난수생성기.범위_내_난수_생성하기(LottoNumber.최소_번호, LottoNumber.최대_번호);
        return new LottoNumber(번호);
    }
}