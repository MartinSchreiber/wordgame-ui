package view

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.input.key.*
import constants.ScreenType
import control.backgroundTasks
import model.WordGame
import view.navigation.AppState

//TODO generativ erzeugen? Das wär cool
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

        else -> false
    }
}

val onValueChange = { wordGame: WordGame, text: String ->
    wordGame.updateWord(
        text
            .uppercase()
            .filter { wordGame.allowedLetters.contains(it) }
    )
}

val onGameOver = { wordGame: WordGame ->
    wordGame.endTime = System.currentTimeMillis()
    AppState.screenState(ScreenType.GameStatistics)
}

@Composable
@Preview
fun WordGame() {
    val wordGame = WordGame(language = AppState.language(), level = AppState.level)
    AppState.wordGame = wordGame

    backgroundTasks(wordGame) { onGameOver(wordGame) }

    MaterialTheme {
        //TODO: Add Enemy-Counter onField(Incoming)
        Column {
            Row {
                Column {
                    GameField(wordGame.gameField)
                    Row {
                        WordQueue(wordGame.wordQueue)
                    }
                    Row {
                        LetterChambers(wordGame.letterChambers)
                    }
                    Row {
                        WordInput(
                            text = wordGame.textInput,
                            word = wordGame.wordInput,
                            onValueChange = { text -> onValueChange(wordGame, text) },
                            onKeyEvent = { ev -> onKeyEvent(wordGame, ev) })
                    }
                }
                Column {
                    WordsTyped(wordGame.typedWords)
                }
            }
        }
    }
}