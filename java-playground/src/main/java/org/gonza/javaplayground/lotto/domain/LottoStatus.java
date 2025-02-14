package org.gonza.javaplayground.lotto.domain;

public enum LottoStatus {
    ISSUED,
    ;

    public boolean isIssued() {
        return this.equals(ISSUED);
    }
}
