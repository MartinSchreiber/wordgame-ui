package view

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.input.key.*
import model.WordGame

//val keyCodes = Util.getKeyCodeToCharMap()
//TODO generativ erzeugen? Das wär cool (also move back into WordInput)
@OptIn(ExperimentalComposeUiApi::class)
val onKeyEvent = { wordGame: WordGame, ev: KeyEvent ->
    when {
        (ev.key == Key.Enter && ev.type == KeyEventType.KeyDown) -> {
            wordGame.addWord()
            true
        }

        (ev.key == Key.Escape && ev.type == KeyEventType.KeyDown) -> {
            wordGame.clearInput()
            true
        }

//        (keyCodes.keys.contains(ev.key.keyCode) && ev.type == KeyEventType.KeyDown) -> {
////            wordGame.type(keyCodes[ev.key.keyCode]!!)
//            println(keyCodes[ev.key.keyCode])
//            true
//        }

        else -> false
    }
}

val onValueChange = { wordGame: WordGame, text: String -> wordGame.updateWord(text) }

@Composable
@Preview
fun WordGame(wordGame: WordGame) {
    MaterialTheme {
        Column {
            LetterChambers(wordGame.letterChambers)
            Row {
                Column {
                    WordsTyped(wordGame.wordsTyped)
                }
                Column {
                    Enemies(wordGame.enemiesOnField)
                }
            }
            Row {
                WordQueue(wordGame.wordQueue.toMutableStateList())
            }
            Row {
                WordInput(
                    text = wordGame.textInput,
                    word = wordGame.wordInput,
                    onValueChange = { text -> onValueChange(wordGame, text) },
                    onKeyEvent = { ev -> onKeyEvent(wordGame, ev) })
            }
        }
    }
}