package org.gonza.javaplayground.lotto;

public class Lotto {
    private final LottoNumbers numbers;
    private final LottoStatus status;

    public static Lotto of(LottoNumbers numbers) {
        return new Lotto(numbers);
    }

    public Lotto(LottoNumbers numbers) {
        this.numbers = numbers;
        this.status = LottoStatus.ISSUED;
    }

    public boolean isIssued() {
        return status.isIssued();
    }
}
