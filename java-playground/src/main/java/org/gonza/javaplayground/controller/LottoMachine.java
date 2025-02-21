package org.gonza.javaplayground.controller;

import java.util.ArrayList;
import org.gonza.javaplayground.model.Lotto;
import org.gonza.javaplayground.model.LottoNumber;
import org.gonza.javaplayground.model.Money;

public class LottoMachine {
    private static final int 최소_구매_가격 = 1000;
    private static final int 구매_단위 = 1000;
    private final RandomGenerator 난수생성기;

    public LottoMachine() {
        this.난수생성기 = new RandomGenerator();
    }

    public ArrayList<Lotto> 로또_구매하기(Money 금액) {
        구매_가능_여부_검증하기(금액);

        int 구매_가능_수량 = 구매_가능_수량_계산하기(금액);
        return 로또_목록_생성하기(구매_가능_수량);
    }

    private void 구매_가능_여부_검증하기(Money 금액) {
        if (금액.금액_가져오기() < 최소_구매_가격) {
            throw new IllegalArgumentException("최소 구매 가격은 " + 최소_구매_가격 + "원 입니다.");
        }
        if (금액.금액_가져오기() % 최소_구매_가격 != 0) {
            throw new IllegalArgumentException(최소_구매_가격 + "원 단위로만 구매 가능합니다.");
        }
    }

    private int 구매_가능_수량_계산하기(Money 금액) {
        return 금액.금액_가져오기() / 구매_단위;
    }

    private ArrayList<Lotto> 로또_목록_생성하기(int 구매_수량) {
        ArrayList<Lotto> 로또_목록 = new ArrayList<>();
        for (int i = 0; i < 구매_수량; i++) {
            Lotto 로또 = 로또_생성하기();
            로또_목록.add(로또);
        }
        return 로또_목록;
    }

    private Lotto 로또_생성하기() {
        Lotto 로또 = new Lotto();
        for (int i = 0; i < 6; i++) {
            LottoNumber 로또_번호 = 로또_번호_생성하기();
            로또.번호_추가하기(로또_번호);
        }
        return 로또;
    }

    private LottoNumber 로또_번호_생성하기() {
        int 번호 = 난수생성기.범위_내_난수_생성하기(LottoNumber.최소_번호, LottoNumber.최대_번호);
        return new LottoNumber(번호);
    }
}
