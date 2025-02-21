package org.gonza.kotlinplayground

import org.gonza.kotlinplayground.ui.InputView
import org.gonza.kotlinplayground.ui.PrintView

class KotlinPlaygroundApplication

fun main(args: Array<String>) {
    val printView = PrintView.getInstance()
    val inputView = InputView.getInstance()
    val runner = LottoRunner(
        printView = printView,
        inputView = inputView,
    )

    runner.run()
}
