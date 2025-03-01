package org.gonza.javaplayground.lotto.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LottoNumberTest {

	@Test
	void LottoNumber를_생성할_수_있다() {
		LottoNumber number = LottoNumber.of(1);

		assertEquals(1, number.getValue());
	}

	@Test
	void LottoNumber는_1_미만일_수_없다() {
		assertThrows(IllegalArgumentException.class, () -> LottoNumber.of(0));
	}

	@Test
	void LottoNumber는_45를_초과할_수_없다() {
		assertThrows(IllegalArgumentException.class, () -> LottoNumber.of(46));
	}

	@Test
	void LottoNumber의_value가_동일하다면_같다() {
		LottoNumber number1 = LottoNumber.of(1);
		LottoNumber number2 = LottoNumber.of(1);

		assertEquals(number1, number2);
	}

	@Test
	void LottoNumber의_value가_동일하지않다면_다르다() {
		LottoNumber number1 = LottoNumber.of(1);
		LottoNumber number2 = LottoNumber.of(2);

		assertNotEquals(number1, number2);
	}
}