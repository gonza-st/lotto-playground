package org.gonza.javaplayground.model;

public class LottoNumber {
    public static final int 최소_번호 = 1;
    public static final int 최대_번호 = 45;

    private final int 번호;

    public LottoNumber(int 번호) {
        번호_유효성_검증하기(번호);
        this.번호 = 번호;
    }

    public int 번호_값_가져오기() {
        return 번호;
    }

    private void 번호_유효성_검증하기(int 번호) {
        if (번호 < 최소_번호 || 번호 > 최대_번호) {
            throw new IllegalArgumentException("잘못된 로또 숫자 범위입니다.");
        }

        if (!String.valueOf(번호).matches("\\d+")) {
            throw new IllegalArgumentException("로또 번호는 숫자여야 합니다.");
        }
    }
}