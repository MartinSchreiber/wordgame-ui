package model

import constants.LetterType

class Word(val letters: List<Letter>) {
    fun getTotalValue(): Double {
        var total = 0.0
        var multi = 1.0

        letters.forEach {
            when (it.type) {
                LetterType.BASIC -> total += it.value
                LetterType.STRONGER -> total += it.value + it.specialValue
                LetterType.MULTIPLY -> multi *= it.specialValue
            }
        }

        return total * multi
    }

    private fun setTotalValues() {
        val multi = letters
            .filter { it.type == LetterType.MULTIPLY }
            .map { it.specialValue }
            .fold(1.0) { f1, f2 -> f1 * f2 }

        letters.forEach {
            when (it.type) {
                LetterType.STRONGER -> it.totalValue = (it.value + it.specialValue) * multi
                else -> it.totalValue = it.value * multi
            }
        }
    }

    override fun toString(): String {
        return letters.joinToString("")
    }
}