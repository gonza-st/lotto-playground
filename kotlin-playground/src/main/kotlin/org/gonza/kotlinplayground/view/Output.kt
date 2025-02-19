package org.gonza.kotlinplayground.view

class Output {
    fun print(value: String) {
        println(value)
    }

    fun print(valueList: List<String>) {
        valueList.forEach { println(it) }
        println()
    }
}
