package model

import constants.LetterType

class Word(val letters: List<Letter>) {
    fun getTotalValue(): Double {
        var total = 0.0
        var multi = 1.0

        letters.forEach {
            when (it.type) {
                LetterType.BASIC -> total += it.value
                LetterType.MULTIPLY -> multi *= it.specialValue
                LetterType.STRONGER -> total += it.value + it.specialValue
            }
        }

        return total * multi
    }

    override fun toString(): String {
        return letters.map { it.letter }.joinToString("")
    }
}