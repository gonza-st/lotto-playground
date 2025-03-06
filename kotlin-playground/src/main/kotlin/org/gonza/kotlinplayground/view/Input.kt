package org.gonza.kotlinplayground.view

import java.util.Scanner

class Input {
    fun int(): Int {
        val scanner = Scanner(System.`in`)
        return scanner.nextInt()
    }

    fun string(): String {
        val scanner = Scanner(System.`in`)
        return scanner.nextLine()
    }

    fun iterString(count: Int): MutableList<String> {
        val manualNumberStringList: MutableList<String> = mutableListOf()

        var tryCount = count
        while (tryCount > 0) {
            manualNumberStringList.add(string())
            tryCount--
        }

        return manualNumberStringList
    }
}
