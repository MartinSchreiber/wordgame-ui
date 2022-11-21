package model

import constants.LetterType

class Word(val letters: MutableList<Letter> = mutableListOf()) {
    fun size(): Int {
        return letters.size
    }

    fun getTotalValue(): Double {
        return letters.sumOf { it.totalValue }
    }

    fun addLetter(letter: Letter) {
        letters.add(letter)
        letters
            .takeIf { letter.type == LetterType.MULTIPLY }
            ?.forEach { it.totalValue *= letter.specialValue }
    }

    fun removeLetter(): Letter {
        val removedLetter = letters.removeLast()
        letters
            .takeIf { removedLetter.type == LetterType.MULTIPLY }
            ?.forEach { it.totalValue /= removedLetter.specialValue }
        return removedLetter
    }

    override fun toString(): String {
        return letters.joinToString("")
    }
}