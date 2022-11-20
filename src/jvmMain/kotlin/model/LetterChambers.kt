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
        val chambersToRemove = chambers.filter { it.open && letters.contains(it.letter) }.toMutableList()
        //TODO ordentlich machen und verhindern, dass alle Instanzen des Buchstabens gelöscht werden
        val openNextChamber = opened.value < visible && chambersToRemove.size == opened.value
        chambers.filter { it.open }.forEach {
            if (chambersToRemove.contains(it)) {
                chambers.remove(it)
                chambersToRemove.remove(it)
                open()
            }
        }

        if (openNextChamber) {
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