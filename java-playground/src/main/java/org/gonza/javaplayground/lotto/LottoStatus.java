package org.gonza.javaplayground.lotto;

public enum LottoStatus {
    ISSUED,
    ;

    public boolean isIssued() {
        return this.equals(ISSUED);
    }
}
