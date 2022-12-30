package view.gameplay

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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
    val painter = if (open) {
        painterResource("svg/letter_chamber_open.svg")
    } else {
        painterResource("svg/letter_chamber_closed.svg")
    }
    Box {
        Image(
            painter = painter,
            alignment = Alignment.Center,
            contentDescription = letter.value?.letter.toString(),
            modifier = Modifier.requiredSize(39f.dp)
        )
        if (open && letter.value != null) {
            Letter(letter = letter.value!!, modifier = Modifier.align(Alignment.Center))
        }
    }
}

//@Composable
//fun LetterChamber(letter: MutableState<Letter?>, open: Boolean) {
//    if (open) {
//        // on null letter is borrowed (in currently typed word)
//        Text(text = letter.value?.toString() ?: "(=)")
//    } else {
//        Text(text = "(?)")
//    }
//}