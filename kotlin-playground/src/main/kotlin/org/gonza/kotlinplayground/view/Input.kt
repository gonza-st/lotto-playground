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
}
