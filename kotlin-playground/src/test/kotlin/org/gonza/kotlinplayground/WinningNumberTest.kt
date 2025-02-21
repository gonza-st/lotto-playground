package org.gonza.kotlinplayground

import org.gonza.kotlinplayground.domain.WinningNumber
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class WinningNumberTest {
    @Test
    fun `당첨 번호를 정상적으로 생성한다`() {
        val validStr = "1, 2, 3, 4, 5, 6"
        val expectedSize = 6

        val winningNumber = WinningNumber.from(validStr)

        assertEquals(expectedSize, winningNumber.numberList.size)
        assertEquals(
            listOf(1, 2, 3, 4, 5, 6),
            winningNumber.numberList.map { it.number }
        )
    }

    @Test
    fun `입력 형식이 잘못된 경우 예외가 발생한다`() {
        val invalidInput = "1, 2, 3, a, 5, 6"

        assertThrows<IllegalArgumentException> {
            WinningNumber.from(invalidInput)
        }
    }

    @Test
    fun `공백이 있어도 정상적으로 생성된다`() {
        val invalidInput = "1,    2,3,   4,5,     6"

        val winningNumber = WinningNumber.from(invalidInput)

        assertEquals(
            listOf(1, 2, 3, 4, 5, 6),
            winningNumber.numberList.map { it.number }
        )

    }
}