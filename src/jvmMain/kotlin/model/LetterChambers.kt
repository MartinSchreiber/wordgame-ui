package model

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList

class LetterChambers(val visible: Int, opened: Int, letters: List<Letter>) {

    val opened = mutableStateOf(opened)
    val chambers = letters
        .shuffled()
        .mapIndexed{ index, letter -> Chamber(letter, index<opened-1) }
        .toMutableStateList()

    fun open(number: Int = 1) {
        if(chambers.filter { it.open }.size < visible) {
            chambers
                .filter { !it.open }
                .subList(0, number)
                .forEach { it.open = true }
        }
    }

    fun removeLetters(letters : List<Letter>) {
        chambers.removeIf { it.open && letters.contains(it.letter) }
        open(letters.size)
        //TODO remove letters from mutableStateList (set typed=true), add letters from main list to fill chambers and set state of accompanying boolean accordingly
    }

    fun loadLetters(letters: List<Letter>) {
        chambers.addAll(letters.map { Chamber(it) })
    }

    fun getOpenLetters() : MutableList<Letter> {
        return chambers.filter { it.open }.map { it.letter }.toMutableList()
    }
}

data class Chamber(val letter: Letter, var open: Boolean = false)