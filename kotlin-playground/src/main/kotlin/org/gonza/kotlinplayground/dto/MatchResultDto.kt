package org.gonza.kotlinplayground.dto

data class MatchResultDto(
    var fifth: Int = 0,
    var fourth: Int = 0,
    var third: Int = 0,
    var second: Int = 0,
    var winner: Int = 0,
) {
    fun toList(): List<Pair<String, Int>> =
        listOf(
            "fifth" to fifth,
            "fourth" to fourth,
            "third" to third,
            "second" to second,
            "winner" to winner,
        )
}
