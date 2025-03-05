package org.gonza.javaplayground.controller;

import java.util.List;
import org.gonza.javaplayground.model.Lotto;
import org.gonza.javaplayground.model.LottoNumber;

public class LottoVerifier {
    private Lotto 당첨번호;
    private LottoNumber 보너스번호;

    public void 당첨번호_설정하기(List<LottoNumber> 당첨번호_배열) {
        this.당첨번호 = 당첨로또_생성하기(당첨번호_배열);
    }

    public void 보너스번호_설정하기(LottoNumber 보너스번호) {
        보너스번호_유효성_검증하기(보너스번호);
        this.보너스번호 = 보너스번호;
    }

    private void 보너스번호_유효성_검증하기(LottoNumber 보너스번호) {
        if (당첨번호.로또번호_포함_여부_확인하기(보너스번호)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    private Lotto 당첨로또_생성하기(List<LottoNumber> 당첨번호_배열) {
        return new Lotto(당첨번호_배열);
    }

    public int 일치_번호_개수_계산하기(Lotto 로또) {
        return (int) 로또.로또번호_목록_가져오기().stream()
                .filter(this::당첨번호_포함_여부_확인하기)
                .count();
    }

    public boolean 보너스번호_일치_확인하기(Lotto 로또) {
        return 로또.로또번호_포함_여부_확인하기(보너스번호);
    }

    private boolean 당첨번호_포함_여부_확인하기(LottoNumber 번호) {
        return 당첨번호.로또번호_목록_가져오기().stream()
                .anyMatch(당첨번호 -> 당첨번호.번호_값_가져오기() == 번호.번호_값_가져오기());
    }
}