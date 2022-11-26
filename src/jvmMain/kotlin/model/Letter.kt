package model

import constants.LetterType

data class Letter(
    val letter: Char,
    val value: Int = 1,
    val type: LetterType = LetterType.BASIC,
    val specialValue: Double = 0.0
) {
    val initTotalValue = {
        when (type) {
            LetterType.STRONGER -> value + specialValue
            else -> value.toDouble()
        }
    }
    var totalValue: Double = initTotalValue()

    override fun toString(): String {
        return when (type) {
            LetterType.BASIC -> "($letter)"
            LetterType.STRONGER -> "[$letter]"
            LetterType.MULTIPLY -> "{$letter}"
        }
    }

    fun resetTotalValue() {
        totalValue = initTotalValue()
    }
}