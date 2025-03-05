package org.gonza.javaplayground.lotto.domain;

public enum LottoType {
	AUTOMATIC,
	MANUAL,
	;

	public boolean isManual() {
		return this.equals(MANUAL);
	}

	public boolean isAutomatic() {
		return this.equals(AUTOMATIC);
	}
}
