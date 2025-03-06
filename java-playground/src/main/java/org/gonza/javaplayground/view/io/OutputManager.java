package org.gonza.javaplayground.view.io;

import java.util.List;
import java.util.Map;
import org.gonza.javaplayground.model.Lotto;
import org.gonza.javaplayground.model.LottoNumber;
import org.gonza.javaplayground.model.Rank;

public class OutputManager {
    public void 구매_금액_입력_안내하기() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void 당첨_번호_입력_안내하기() {
        System.out.println();
        System.out.println("당첨 번호를 입력해 주세요.");
    }

    public void 보너스_번호_입력_안내하기() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    public void 구매_로또_목록_출력하기(List<Lotto> 구매_로또_목록) {
        System.out.println("\n구매한 로또 목록");
        for (Lotto 로또 : 구매_로또_목록) {
            로또_한줄_출력하기(로또);
        }
    }

    private void 로또_한줄_출력하기(Lotto 로또) {
        for (LottoNumber 번호 : 로또.로또번호_목록_가져오기()) {
            System.out.print(번호.번호_값_가져오기() + " ");
        }
        System.out.println();
    }

    public void 수동_구매_개수_입력_안내하기() {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
    }

    public void 수동_구매_번호_입력_안내하기() {
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
    }

    public void 구매_결과_출력하기(int 수동구매수량, int 자동구매수량) {
        System.out.printf("\n수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", 수동구매수량, 자동구매수량);
    }

    public void 당첨_통계_출력하기(Map<Rank, Integer> 당첨_통계, double 수익률) {
        System.out.println("\n당첨 통계\n---------");
        System.out.printf("3개 일치 (5,000원)- %d개\n", 당첨_통계.getOrDefault(Rank.FIFTH, 0));
        System.out.printf("4개 일치 (50,000원)- %d개\n", 당첨_통계.getOrDefault(Rank.FOURTH, 0));
        System.out.printf("5개 일치 (1,500,000원)- %d개\n", 당첨_통계.getOrDefault(Rank.THIRD, 0));
        System.out.printf("5개 일치, 보너스 볼 일치(30,000,000원) - %d개\n", 당첨_통계.getOrDefault(Rank.SECOND, 0));
        System.out.printf("6개 일치 (2,000,000,000원)- %d개\n", 당첨_통계.getOrDefault(Rank.FIRST, 0));
        System.out.printf("총 수익률은 %.2f입니다.\n", 수익률);
    }
}