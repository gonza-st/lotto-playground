package org.gonza.javaplayground.lotto.domain;

import java.util.Objects;

import org.gonza.javaplayground.lotto.LottoConstant;

public class LottoNumber {
	public static LottoNumber of(int number) {
		validateNumber(number);
		return new LottoNumber(number);
	}

	private final int value;

	public LottoNumber(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	private static void validateNumber(int number) {
		if (number < LottoConstant.LOTTO_MIN_NUMBER || number > LottoConstant.LOTTO_MAX_NUMBER) {
			throw new IllegalArgumentException("Number must be between 1 and 45");
		}
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass())
			return false;
		LottoNumber that = (LottoNumber)o;
		return value == that.value;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(value);
	}
}
