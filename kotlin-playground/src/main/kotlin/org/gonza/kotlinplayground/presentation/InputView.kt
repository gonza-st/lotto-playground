package org.gonza.kotlinplayground.presentation

class InputView {
    fun read() = readlnOrNull()

    fun readMultipleByCount(count: Int): List<String?> {
        val lineList = mutableListOf<String?>()

        repeat(count) {
            val readLine = read()
            lineList.add(readLine)
        }

        return lineList
    }
}
