package org.gonza.javaplayground.model;

import java.util.ArrayList;

public class Lotto {
    private final ArrayList<LottoNumber> 번호_목록;

    public Lotto() {
        this.번호_목록 = new ArrayList<>();
    }

    public ArrayList<LottoNumber> 번호_목록_가져오기() {
        return new ArrayList<>(번호_목록);
    }

    public void 번호_추가하기(LottoNumber 로또_번호) {
        번호_목록.add(로또_번호);
    }
}
