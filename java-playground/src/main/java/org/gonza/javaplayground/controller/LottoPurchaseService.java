package org.gonza.javaplayground.controller;

import java.util.ArrayList;
import java.util.List;
import org.gonza.javaplayground.model.Lotto;
import org.gonza.javaplayground.model.Money;

public class LottoPurchaseService {
    private static final int 최소_구매_가격 = 1000;
    private static final int 구매_단위 = 1000;

    private final LottoMachine 로또머신;

    public LottoPurchaseService(LottoMachine 로또머신) {
        this.로또머신 = 로또머신;
    }

    public List<Lotto> 로또_구매하기(Money 금액, int 수동구매수량, List<Lotto> 수동구매로또) {
        구매_가능_여부_검증하기(금액);
        int 총_구매_가능_수량 = 구매_가능_수량_계산하기(금액);

        if (수동구매수량 > 총_구매_가능_수량) {
            throw new IllegalArgumentException("수동 구매 수량이 총 구매 가능 수량을 초과했습니다.");
        }

        List<Lotto> 전체_로또_목록 = new ArrayList<>(수동구매로또);
        int 자동구매수량 = 총_구매_가능_수량 - 수동구매수량;

        전체_로또_목록.addAll(로또머신.로또_생성하기(자동구매수량));
        return 전체_로또_목록;
    }

    public void 구매_가능_여부_검증하기(Money 금액) {
        if (금액.금액_가져오기() < 최소_구매_가격) {
            throw new IllegalArgumentException("최소 구매 가격은 " + 최소_구매_가격 + "원 입니다.");
        }
        if (금액.금액_가져오기() % 최소_구매_가격 != 0) {
            throw new IllegalArgumentException(최소_구매_가격 + "원 단위로만 구매 가능합니다.");
        }
    }

    public int 구매_가능_수량_계산하기(Money 금액) {
        return 금액.금액_가져오기() / 구매_단위;
    }
}