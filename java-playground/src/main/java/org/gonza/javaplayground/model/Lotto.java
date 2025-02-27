package org.gonza.javaplayground.model;

import java.util.HashSet;
import java.util.List;

public class Lotto {
    public static final int 로또_번호_개수 = 6;

    private final List<LottoNumber> 로또번호들;

    public Lotto(List<LottoNumber> 로또번호들) {
        로또_번호_개수_검증하기(로또번호들);
        로또_번호_중복_검증하기(로또번호들);
        this.로또번호들 = 로또번호들;
    }

    public List<LottoNumber> 로또번호_목록_가져오기() {
        return List.copyOf(로또번호들);
    }

    private void 로또_번호_개수_검증하기(List<LottoNumber> 로또번호들) {
        if (로또번호들.size() != 로또_번호_개수) {
            throw new IllegalArgumentException(
                    String.format("로또 번호는 %d개여야 합니다.", 로또_번호_개수));
        }
    }

    private void 로또_번호_중복_검증하기(List<LottoNumber> 로또번호들) {
        if (new HashSet<>(로또번호들).size() != 로또번호들.size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }
}