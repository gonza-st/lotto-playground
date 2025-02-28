package org.gonza.kotlinplayground.utils

object NumberConverter {
    fun convert(str: String): Int =
        try {
            str.toInt()
        } catch (e: NumberFormatException) {
            throw e
        }
}