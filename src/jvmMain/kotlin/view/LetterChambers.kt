package view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import model.Letter
import model.LetterChambers
import model.Word

@Composable
fun LetterChambers(letterChambers: LetterChambers) {

}

@Composable
fun LetterChamber(letter: Letter, opened: Boolean) {
    if(opened) {
        Text(text = letter.letter.toString())
    }
    else {
        Text(text = "?")
    }
}