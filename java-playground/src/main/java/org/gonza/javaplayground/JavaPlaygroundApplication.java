package org.gonza.javaplayground;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.gonza.javaplayground.controller.Calculator;
import org.gonza.javaplayground.controller.LottoMachine;
import org.gonza.javaplayground.controller.LottoPurchaseService;
import org.gonza.javaplayground.model.Lotto;
import org.gonza.javaplayground.model.LottoNumber;
import org.gonza.javaplayground.model.Money;
import org.gonza.javaplayground.model.Rank;
import org.gonza.javaplayground.view.io.InputManager;
import org.gonza.javaplayground.view.io.OutputManager;

public class JavaPlaygroundApplication {
    public static void main(String[] args) {
        OutputManager 프린터 = new OutputManager();
        InputManager 입력관리자 = new InputManager();
        LottoMachine 로또기계 = new LottoMachine();
        LottoPurchaseService 로또구매서비스 = new LottoPurchaseService(로또기계);
        Calculator 계산기 = new Calculator();

        프린터.구매_금액_입력_안내하기();
        Money 구매_금액 = new Money(입력관리자.정수_읽기());

        프린터.수동_구매_개수_입력_안내하기();
        int 수동구매수량 = 입력관리자.정수_읽기();

        List<Lotto> 수동구매로또 = new ArrayList<>();
        String regex = ", ";
        if (수동구매수량 > 0) {
            프린터.수동_구매_번호_입력_안내하기();
            for (int i = 0; i < 수동구매수량; i++) {
                String[] 번호들 = 입력관리자.문자열_읽기().split(regex);
                List<LottoNumber> 로또번호목록 = new ArrayList<>();
                for (String 번호 : 번호들) {
                    로또번호목록.add(new LottoNumber(Integer.parseInt(번호)));
                }
                수동구매로또.add(new Lotto(로또번호목록));
            }
        }

        List<Lotto> 구매_로또_목록 = 로또구매서비스.로또_구매하기(구매_금액, 수동구매수량, 수동구매로또);
        int 자동구매수량 = 구매_로또_목록.size() - 수동구매수량;

        프린터.구매_결과_출력하기(수동구매수량, 자동구매수량);
        프린터.구매_로또_목록_출력하기(구매_로또_목록);

        프린터.당첨_번호_입력_안내하기();
        입력관리자.문자열_읽기();
        String[] 당첨_번호_문자열 = 입력관리자.문자열_읽기().split(regex);

        List<LottoNumber> 당첨_번호 = new ArrayList<>(6);
        for (String 번호 : 당첨_번호_문자열) {
            당첨_번호.add(new LottoNumber(Integer.parseInt(번호)));
        }

        계산기.당첨번호_설정하기(당첨_번호);

        프린터.보너스_번호_입력_안내하기();
        int 보너스_번호_값 = 입력관리자.정수_읽기();
        LottoNumber 보너스_번호 = new LottoNumber(보너스_번호_값);
        계산기.보너스번호_설정하기(보너스_번호);

        Map<Rank, Integer> 당첨_통계 = 계산기.당첨_통계_계산하기(구매_로또_목록);
        double 수익률 = 계산기.수익률_계산하기(당첨_통계, 구매_금액.금액_가져오기());

        프린터.당첨_통계_출력하기(당첨_통계, 수익률);

        입력관리자.종료하기();
    }
}