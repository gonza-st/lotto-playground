package org.gonza.javaplayground.lotto.domain;

public class Lotto {
    private final LottoNumber numbers;
    private final LottoStatus status;

    public static Lotto of(LottoNumber numbers) {
        return new Lotto(numbers);
    }

    private Lotto(LottoNumber numbers) {
        this.numbers = numbers;
        this.status = LottoStatus.ISSUED;
    }

    public boolean isIssued() {
        return status.isIssued();
    }
}
