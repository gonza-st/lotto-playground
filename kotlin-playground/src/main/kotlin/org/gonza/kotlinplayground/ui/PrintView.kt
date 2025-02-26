package org.gonza.kotlinplayground.ui

class PrintView {

    fun printOnly(message: String) {
        print(message = message)
    }

    fun printWithLine(message: String) {
        println(message = message)
    }

    companion object {
        private var instance: PrintView? = null

        fun getInstance(): PrintView {
            return instance ?: PrintView().apply {
                instance = this
            }
        }
    }
}