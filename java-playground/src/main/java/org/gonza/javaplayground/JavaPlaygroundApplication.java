package org.gonza.javaplayground;

import java.util.ArrayList;
import java.util.Map;
import org.gonza.javaplayground.controller.Calculator;
import org.gonza.javaplayground.controller.LottoMachine;
import org.gonza.javaplayground.model.Lotto;
import org.gonza.javaplayground.model.LottoNumber;
import org.gonza.javaplayground.model.Money;
import org.gonza.javaplayground.view.Printer;
import org.gonza.javaplayground.view.io.InputManager;

public class JavaPlaygroundApplication {
    public static void main(String[] args) {
        Printer 프린터 = new Printer();
        InputManager 입력관리자 = new InputManager();
        LottoMachine 로또기계 = new LottoMachine();
        Calculator 계산기 = new Calculator();

        프린터.구매_금액_입력_안내하기();
        Money 구매_금액 = new Money(입력관리자.정수_읽기());

        ArrayList<Lotto> 구매_로또_목록 = 로또기계.로또_구매하기(구매_금액);

        프린터.구매_로또_목록_출력하기(구매_로또_목록);

        프린터.당첨_번호_입력_안내하기();
        입력관리자.문자열_읽기();
        String[] 당첨_번호_문자열 = 입력관리자.문자열_읽기().split(", ");

        LottoNumber[] 당첨_번호 = new LottoNumber[6];
        for (int i = 0; i < 6; i++) {
            당첨_번호[i] = new LottoNumber(Integer.parseInt(당첨_번호_문자열[i]));
        }

        계산기.당첨번호_설정하기(당첨_번호);
        Map<Integer, Integer> 당첨_통계 = 계산기.당첨_통계_계산하기(구매_로또_목록);
        double 수익률 = 계산기.수익률_계산하기(당첨_통계, 구매_금액.금액_가져오기());

        프린터.당첨_통계_출력하기(당첨_통계, 수익률);

        입력관리자.종료하기();
    }
}
