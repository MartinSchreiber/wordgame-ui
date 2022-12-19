package model

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import constants.LetterType

class LetterChambers(val visible: Int, var opened: Int, letters: List<Letter>) {

    val chambers = letters
        .shuffled()
        .mapIndexed { index, letter -> Chamber(letter, index < opened) }
        .toMutableStateList()

    private fun open(number: Int = 1) {
        if (chambers.filter { it.open }.size < visible) {
            chambers
                .filter { !it.open }
                .takeIf { it.isNotEmpty() }
                ?.subList(0, number)
                ?.forEach { it.open = true }
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

        if (opened < visible && indices.size >= opened) {
            opened += 1
            open()
        }
    }

    fun borrowLetter(char: Char): Pair<Letter, Int>? {
        chambers.forEachIndexed { ind, chamber ->
            if (chamber.open && chamber.letter.value != null && chamber.letter.value!!.letter == char) {
                val retVal = Pair(chamber.letter.value!!, ind)
                chamber.letter.value = null
                return retVal
            }
        }
        return null
    }

    fun returnLetters(letters: List<Pair<Letter, Int>>) {
        letters.forEach { chambers[it.second].letter.value = it.first }
    }

    fun loadLetters(letters: List<Letter>) {
        letters.forEach { it.resetTotalValue() }

        letters
            .filter { it.type != LetterType.BASIC }
            .map { Chamber(it) }
            .forEach {
                it.open = chambers.size < opened
                chambers.add(it)
            }
    }

    class Chamber(letter: Letter?, var open: Boolean = false) {
        var letter = mutableStateOf(letter)
    }
}