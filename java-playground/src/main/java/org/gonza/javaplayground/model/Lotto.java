package org.gonza.javaplayground.model;

import java.util.HashSet;
import java.util.List;

public class Lotto {
    public static final int 로또_번호_개수 = 6;

    private final List<LottoNumber> 로또번호들;

    public Lotto(List<LottoNumber> 로또번호들) {
        검증하기(로또번호들);
        this.로또번호들 = 로또번호들;
    }

    /**
     * 특정 번호가 이 로또에 포함되어 있는지 여부를 반환합니다.
     */
    public boolean 로또번호_포함_여부_확인하기(LottoNumber 번호) {
        return 로또번호들.stream()
                .anyMatch(lottoNumber -> lottoNumber.번호_값_가져오기() == 번호.번호_값_가져오기());
    }

    /**
     * 현재 로또 번호들의 불변 복사본을 반환합니다.
     */
    public List<LottoNumber> 로또번호_목록_가져오기() {
        return List.copyOf(로또번호들);
    }

    /**
     * 로또 번호들에 대한 모든 검증을 수행합니다.
     */
    private void 검증하기(List<LottoNumber> 로또번호들) {
        로또_번호_개수_검증하기(로또번호들);
        로또_번호_중복_검증하기(로또번호들);
    }

    private void 로또_번호_개수_검증하기(List<LottoNumber> 로또번호들) {
        if (로또번호들.size() != 로또_번호_개수) {
            throw new IllegalArgumentException(
                    String.format("로또 번호는 %d개여야 합니다.", 로또_번호_개수)
            );
        }
    }

    private void 로또_번호_중복_검증하기(List<LottoNumber> 로또번호들) {
        if (new HashSet<>(로또번호들).size() != 로또번호들.size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }
}
