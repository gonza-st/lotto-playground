package org.gonza.javaplayground.view;

import java.util.ArrayList;
import java.util.Map;
import org.gonza.javaplayground.model.Lotto;
import org.gonza.javaplayground.model.LottoNumber;

public class Printer {
    public void 구매_금액_입력_안내하기() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void 당첨_번호_입력_안내하기() {
        System.out.println();
        System.out.println("당첨 번호를 입력해 주세요.");
    }

    public void 구매_로또_목록_출력하기(ArrayList<Lotto> 구매_로또_목록) {
        System.out.println("\n구매한 로또 목록");
        for (Lotto 로또 : 구매_로또_목록) {
            로또_한줄_출력하기(로또);
        }
    }

    private void 로또_한줄_출력하기(Lotto 로또) {
        for (LottoNumber 번호 : 로또.번호_목록_가져오기()) {
            System.out.print(번호.번호_값_가져오기() + " ");
        }
        System.out.println();
    }

    public void 당첨_통계_출력하기(Map<Integer, Integer> 당첨_통계, double 수익률) {
        System.out.println("\n당첨 통계\n---------");
        System.out.printf("3개 일치 (5,000원)- %d개\n", 당첨_통계.get(3));
        System.out.printf("4개 일치 (50,000원)- %d개\n", 당첨_통계.get(4));
        System.out.printf("5개 일치 (1,500,000원)- %d개\n", 당첨_통계.get(5));
        System.out.printf("6개 일치 (2,000,000,000원)- %d개\n", 당첨_통계.get(6));
        System.out.printf("총 수익률은 %.2f입니다.\n", 수익률);
    }


}
