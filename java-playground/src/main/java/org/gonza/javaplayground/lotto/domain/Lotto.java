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

    public void verify(LottoNumber lottoNumber) {
        int matchedCount = this.numbers.matchBy(lottoNumber);
        determineResult(matchedCount);
    }

    private void determineResult(int matchedCount) {
        if (matchedCount >= 3) {
            win(matchedCount);
        }

        if (matchedCount < 3) {
            lose();
        }
    }

    private void lose() {
        this.status = LottoStatus.LOST;
    }

    private void win(int matchedCount) {
        this.rank = Rank.of(matchedCount);
        this.status = LottoStatus.WON;
    }
}
