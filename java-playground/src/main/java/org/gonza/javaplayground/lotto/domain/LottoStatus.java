package org.gonza.javaplayground.lotto.domain;

public enum LottoStatus {
    ISSUED,
    WON,
    LOST,
    ;

    public boolean isIssued() {
        return this.equals(ISSUED);
    }

    public boolean isWon() {
        return this.equals(WON);
    }
}
