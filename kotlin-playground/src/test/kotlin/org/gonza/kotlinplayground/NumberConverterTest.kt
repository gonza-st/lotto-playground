package org.gonza.kotlinplayground

import org.gonza.kotlinplayground.utils.NumberConverter
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class NumberConverterTest {
    @Test
    fun `문자를 숫자로 변환한다`() {
        val validNumberString = "1"
        val expectedNumber = 1

        val result = NumberConverter.convert(str = validNumberString)

        assertEquals(expectedNumber, result)
    }

    @Test
    fun `숫자로 변환 불가능한 경우 예외를 던진다`() {
        val invalidNumberString = "invalid"

        assertThrows<NumberFormatException> { NumberConverter.convert(str = invalidNumberString) }
    }
}