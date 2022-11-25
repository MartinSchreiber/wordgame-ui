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
        return Word(letters.map { it }.toMutableList())
    }

    override fun toString(): String {
        return letters.joinToString("")
    }
}