package view

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
fun LetterChamber(letter: Letter, open: Boolean) {
    if(open) {
        Text(text = letter.letter.toString())
    }
    else {
        Text(text = "?")
    }
}