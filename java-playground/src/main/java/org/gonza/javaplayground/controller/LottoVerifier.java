package org.gonza.javaplayground.controller;

import java.util.List;
import org.gonza.javaplayground.model.Lotto;
import org.gonza.javaplayground.model.LottoNumber;

public class LottoVerifier {
    private Lotto 당첨번호;

    public void 당첨번호_설정하기(List<LottoNumber> 당첨번호_배열) {
        this.당첨번호 = 당첨로또_생성하기(당첨번호_배열);
    }

    private Lotto 당첨로또_생성하기(List<LottoNumber> 당첨번호_배열) {
        return new Lotto(당첨번호_배열);
    }

    public int 일치_번호_개수_계산하기(Lotto 로또) {
        return (int) 로또.로또번호_목록_가져오기().stream()
                .filter(this::당첨번호_포함_여부_확인하기)
                .count();
    }

    private boolean 당첨번호_포함_여부_확인하기(LottoNumber 번호) {
        return 당첨번호.로또번호_목록_가져오기().stream()
                .anyMatch(당첨번호 -> 당첨번호.번호_값_가져오기() == 번호.번호_값_가져오기());
    }
}