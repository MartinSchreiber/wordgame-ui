package model

import constants.LetterType

data class Letter(
    val letter: Char,
    val value: Int = 1,
    val type: LetterType = LetterType.BASIC,
    val specialValue: Double = 0.0
)