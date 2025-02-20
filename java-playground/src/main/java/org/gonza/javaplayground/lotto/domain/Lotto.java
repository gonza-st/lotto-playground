package org.gonza.javaplayground.lotto.domain;

public class Lotto {
    private final LottoNumber numbers;
    private LottoStatus status;
    private Rank rank;

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

    public boolean isWon() {
        return this.status.isWon();
    }

    public void matchBy(LottoNumber lottoNumber) {
        int matchedCount = this.numbers.matchBy(lottoNumber);

        if (matchedCount >= 3) {
            this.rank = Rank.of(matchedCount);
            this.status = LottoStatus.WON;
        }

        if (matchedCount < 3) {
            this.status = LottoStatus.LOST;
        }
    }
}
