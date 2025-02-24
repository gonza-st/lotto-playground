package org.gonza.kotlinplayground

import Lotto
import org.gonza.kotlinplayground.domain.LottoIssuer
import org.gonza.kotlinplayground.domain.Lottos
import org.gonza.kotlinplayground.domain.YieldCalculator
import org.gonza.kotlinplayground.dto.MatchResultDto
import org.gonza.kotlinplayground.view.Input
import org.gonza.kotlinplayground.view.Output

class KotlinPlaygroundApplication

fun main(args: Array<String>) {
    val input = Input()
    val output = Output()

    output.print("구입금액을 입력해 주세요.")
    val cost: Int = input.int()

    val lottoIssuer = LottoIssuer()
    val amount: Int = lottoIssuer.amount(money = cost)
    output.print("${amount}개를 구매했습니다.")

    val lottos: Lottos = lottoIssuer.buy(amount = amount)
    output.print(valueList = lottos.print())

    output.print("당첨 번호를 입력해 주세요.")
    val winningNumber = input.string()
    val winningLotto = Lotto.create(winningLottoNumber = winningNumber)

    output.print("당첨 통계\n" + "---------")
    val matchResult: MatchResultDto = lottos.matchResult(winningLotto = winningLotto)

    output.print("3개 일치 (5,000원)- ${matchResult.three}개")
    output.print("4개 일치 (50,000원)- ${matchResult.four}개")
    output.print("5개 일치 (1,500,000원)- ${matchResult.five}개")
    output.print("6개 일치 (2,000,000,000원)- ${matchResult.six}개")

    val calculator = YieldCalculator()
    val result: Double = calculator.yield(cost = cost, matchResult = matchResult)

    output.print("총 수익률은 ${result}입니다.")
}
