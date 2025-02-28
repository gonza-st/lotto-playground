package org.gonza.kotlinplayground

import org.gonza.kotlinplayground.view.Input
import org.gonza.kotlinplayground.view.Output

class KotlinPlaygroundApplication

fun main(args: Array<String>) {
    val lottoGameController = LottoGameController(input = Input(), output = Output())
    lottoGameController.run()
}
