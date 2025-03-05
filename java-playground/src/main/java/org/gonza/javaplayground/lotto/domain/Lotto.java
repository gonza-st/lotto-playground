package org.gonza.javaplayground.lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

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

    public void verify(LottoNumbers matchNumbers, LottoNumber bonusNumber) {
        int matchedCount = this.numbers.matchBy(matchNumbers);
        boolean matchedBonus = this.numbers.matchBy(bonusNumber);

        determineResult(matchedCount, matchedBonus);
    }

    private void determineResult(int matchedCount, boolean matchedBonus) {
        this.rank = Rank.valueOf(matchedCount, matchedBonus);

        if (this.rank.equals(Rank.MISS)) {
            this.status = LottoStatus.LOST;
        }

        if (!this.rank.equals(Rank.MISS)) {
            this.status = LottoStatus.WON;
        }
    }

    public List<Integer> getNumberValues() {
        return this.numbers.getNumbers().stream()
            .map(LottoNumber::getValue)
            .collect(Collectors.toList());
    }

    public boolean isManual() {
        return this.numbers.isManual();
    }

    public boolean isAutomatic() {
        return this.numbers.isAutomatic();
    }
}
