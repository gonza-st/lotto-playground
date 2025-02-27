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

    public boolean isLost() {
        return this.status.isLost();
    }

    public Rank getRank() {
        return this.rank;
    }

    public void verify(LottoNumber lottoNumber) {
        int matchedCount = this.numbers.matchBy(lottoNumber);
        determineResult(matchedCount);
    }

    private void determineResult(int matchedCount) {
        this.rank = Rank.valueOf(matchedCount);

        if (this.rank.equals(Rank.MISS)) {
            this.status = LottoStatus.LOST;
        }

        if (!this.rank.equals(Rank.MISS)) {
            this.status = LottoStatus.WON;
        }
    }
}
