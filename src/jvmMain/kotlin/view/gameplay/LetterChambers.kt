package view.gameplay

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import model.Letter
import model.LetterChambers

@Composable
fun LetterChambers(letterChambers: LetterChambers) {
    Row {
        letterChambers.chambers.forEach {
            LetterChamber(it.letter, it.open)
        }
    }
}

@Composable
fun LetterChamber(letter: MutableState<Letter?>, open: Boolean) {
    if (open) {
        Text(text = letter.value?.toString() ?: "(=)")
    } else {
        Text(text = "(?)")
    }
}