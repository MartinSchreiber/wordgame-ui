package view.gameplay

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import model.Letter
import model.LetterChambers

@Composable
fun LetterChambers(letterChambers: LetterChambers) {
    Row {
        for (i in 0 until letterChambers.visible) {
            if (letterChambers.chambers.size > i) {
                LetterChamber(letterChambers.chambers[i].letter, letterChambers.chambers[i].open)
            } else {
                LetterChamber(letter = mutableStateOf(null), open = i < letterChambers.opened)
            }
        }
    }
}

@Composable
fun LetterChamber(letter: MutableState<Letter?>, open: Boolean) {
    if (open) {
        // on null letter is borrowed (in currently typed word)
        Text(text = letter.value?.toString() ?: "(=)")
    } else {
        Text(text = "(?)")
    }
}