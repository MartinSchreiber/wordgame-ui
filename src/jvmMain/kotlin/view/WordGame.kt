package view

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.input.key.*
import model.WordGame

//TODO generativ erzeugen? Das wär cool
@OptIn(ExperimentalComposeUiApi::class)
val hotkeys = { wordGame: WordGame, ev: KeyEvent ->
    when {
        (ev.key == Key.Enter && ev.type == KeyEventType.KeyDown) -> {
            wordGame.addWord()
            true
        }

        (ev.key == Key.Escape && ev.type == KeyEventType.KeyDown) -> {
            wordGame.clearInput()
            true
        }

        else -> false
    }
}

@Composable
@Preview
fun WordGame(wordGame: WordGame) {
    MaterialTheme {
        Column {
            Row {
                Column {
                    WordsTyped(wordGame.wordsTyped)
                }
                Column {
                    Enemies(wordGame.enemiesOnField)
                }
            }
            Row {
                WordQueue(wordGame.wordQueue.getMutableStateList())
            }
            Row {
                WordInput(wordGame.wordInput) { ev -> hotkeys(wordGame, ev) }
            }
        }
    }
}