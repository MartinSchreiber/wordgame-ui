package model

import constants.LetterType

class Word(val letters: MutableList<Letter> = mutableListOf()) {
    fun size(): Int {
        return letters.size
    }

    fun getTotalValue(): Float {
        return letters.map { it.totalValue }.sum()
    }

    fun addLetter(letter: Letter) {
        letters.add(letter)

        if (letter.type == LetterType.MULTIPLY) {
            letters.forEach { it.totalValue *= letter.specialValue }
        }
    }

    fun removeLastLetter(): Letter {
        val removedLetter = letters.removeLast()

        if (removedLetter.type == LetterType.MULTIPLY) {
            letters.forEach { it.totalValue /= removedLetter.specialValue }
        }

        return removedLetter
    }

    fun removeFirstLetter(): Letter {
        return letters.removeFirst()
    }

    fun copy(): Word {
        return Word(letters.map { it.copy() }.toMutableList())
    }

    override fun toString(): String {
        return letters.joinToString("")
    }

    fun toPlainString(): String {
        return letters.joinToString(separator = "", transform = { it.letter.toString() })
    }
}