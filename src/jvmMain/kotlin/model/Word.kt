package model

import constants.LetterType

class Word(val letters: MutableList<Letter> = mutableListOf()) {
    fun prepareForFiring() {
        var factor = 1f

        letters.filter { it.type == LetterType.MULTIPLY }.forEach { factor *= it.specialValue }

        letters.forEach { it.totalValue *= factor }
    }

    fun size(): Int = letters.size

    fun totalValue(): Float = letters.map { it.totalValue }.sum()

    fun addLetter(letter: Letter) = letters.add(letter)

    fun removeLastLetter(): Letter = letters.removeLast()

    fun removeFirstLetterOrNull(): Letter? = letters.removeFirstOrNull()

    fun copy(): Word = Word(letters.map { it.copy() }.toMutableList())

    fun toPlainString(): String = letters.joinToString(separator = "", transform = { it.letter.toString() })

    override fun toString(): String = letters.joinToString("")
}