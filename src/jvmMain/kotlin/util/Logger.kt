package util

import model.Word

class Logger {
    fun log(word: Word) {
        val values = word.letters.map { it.value }
        println("$word is a ${word.getTotalValue()} point word ")
        println("${values.joinToString("+")} = ${word.getTotalValue()}")
    }

    fun logWordFailure(input: String) {
        println("$input is not a word")
    }
}