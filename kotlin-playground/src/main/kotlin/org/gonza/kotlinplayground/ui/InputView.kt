package org.gonza.kotlinplayground.ui

class InputView {

    fun input(): String {
        val input: String = readlnOrNull().orEmpty()
        return input
    }

    companion object {
        private var instance: InputView? = null

        fun getInstance(): InputView {
            return instance ?: InputView().apply {
                instance = this
            }
        }
    }
}