package org.gonza.javaplayground.lotto.domain;

public class Lotto {
    private final LottoNumbers numbers;
    private LottoStatus status;
    private Rank rank;

    public static Lotto of(LottoNumbers numbers) {
        return new Lotto(numbers);
    }

    private Lotto(LottoNumbers numbers) {
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

    public void verify(LottoNumbers lottoNumbers) {
        int matchedCount = this.numbers.matchBy(lottoNumbers);
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
