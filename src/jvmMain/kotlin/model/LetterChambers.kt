package model

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import constants.LetterType

class LetterChambers(private val visible: Int, opened: Int, letters: List<Letter>) {

    private val opened = mutableStateOf(opened)
    val chambers = letters
        .shuffled()
        .mapIndexed { index, letter -> Chamber(letter, index < opened) }
        .toMutableStateList()

    private fun open(number: Int = 1) {
        if (chambers.filter { it.open }.size < visible) {
            chambers
                .filter { !it.open }
                .subList(0, number)
                .forEach { it.open = true }
        }
    }

    fun remove(indices: List<Int>) {
        indices
            .filter { it >= 0 }
            .sortedDescending()
            .forEach {
                chambers.removeAt(it)
                open()
            }

        if (opened.value < visible && indices.size >= opened.value) {
            opened.value += 1
            open()
        }
    }

    fun borrowLetter(char: Char): Pair<Letter, Int>? {
        chambers.forEachIndexed { ind, chamber ->
            if (chamber.open && chamber.letter != null && chamber.letter!!.letter == char) {
                val retVal = Pair(chamber.letter!!, ind)
                chamber.letter = null
                return retVal
            }
        }
        return null
    }

    fun returnLetters(letters: List<Pair<Letter, Int>>) {
        letters.forEach { chambers[it.second].letter = it.first }
    }

    fun loadLetters(letters: List<Letter>) {
        letters.forEach { it.resetTotalValue() }

        chambers.addAll(letters.filter { it.type != LetterType.BASIC }.map { Chamber(it) })
    }
}

data class Chamber(var letter: Letter?, var open: Boolean = false)