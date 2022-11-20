package model

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import constants.LetterType

class LetterChambers(val visible: Int, opened: Int, letters: List<Letter>) {

    val opened = mutableStateOf(opened)
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

    fun remove(letters: List<Letter>) {
        val removeInd = letters
            .map { letter -> chambers.indexOfFirst { it.open && it.letter == letter } }
            .filter { it >= 0 }.sortedDescending()

        removeInd.forEach {
            chambers.removeAt(it)
            open()
        }

        if (opened.value < visible && removeInd.size >= opened.value) {
            opened.value += 1
            open()
        }
    }

    fun loadLetters(letters: List<Letter>) {
        chambers.addAll(letters.filter { it.type != LetterType.BASIC }.map { Chamber(it) })
    }

    fun getOpenLetters(): MutableList<Letter> {
        return chambers.filter { it.open }.map { it.letter }.toMutableList()
    }
}

data class Chamber(val letter: Letter, var open: Boolean = false)