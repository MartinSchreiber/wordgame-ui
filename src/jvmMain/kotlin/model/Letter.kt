package model

import constants.LetterType

data class Letter(
    val letter: Char,
    val value: Int = 1,
    val level: Int = 0,
    val type: LetterType = LetterType.BASIC,
    val specialValue: Float = 0f
) {
    var totalValue: Float = initTotalValue()

    override fun toString(): String {
        return when (type) {
            LetterType.BASIC -> "($letter)"
            LetterType.STRONGER -> "[$letter]"
            LetterType.MULTIPLY -> "{$letter}"
        }
    }

    fun toExtendedString(): String {
        return when (type) {
            LetterType.BASIC -> "($letter|$level)"
            LetterType.STRONGER -> "[$letter|$level]"
            LetterType.MULTIPLY -> "{$letter|$level}"
        }
    }

    fun resetTotalValue() {
        totalValue = initTotalValue()
    }

    private fun initTotalValue(): Float {
        return when (type) {
            LetterType.STRONGER -> value + specialValue
            else -> value.toFloat()
        }
    }
}