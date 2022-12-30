package view.gameplay

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.*
import constants.ScreenType
import control.backgroundTasks
import model.Word
import model.WordGame
import view.components.SimpleButton
import view.components.WordList
import view.navigation.AppState

@OptIn(ExperimentalComposeUiApi::class)
val directionKeys = listOf(Key.DirectionUp, Key.DirectionLeft, Key.DirectionRight, Key.DirectionDown)

@OptIn(ExperimentalComposeUiApi::class)
val onKeyEvent = { wordGame: WordGame, ev: KeyEvent ->
    when {
        // add word to queue
        (ev.key == Key.Enter && ev.type == KeyEventType.KeyDown) -> {
            wordGame.enqueueWord()
            true
        }

        // clear input
        (ev.key == Key.Escape && ev.type == KeyEventType.KeyDown) -> {
            wordGame.clearInput()
            true
        }

        // prevent movement of text-cursor
        (directionKeys.contains(ev.key) && ev.type == KeyEventType.KeyDown) -> {
            true
        }

        // disable text selection
        (ev.key == Key.A && ev.type == KeyEventType.KeyDown && ev.isCtrlPressed) -> {
            true
        }

        // disable text pasting
        (ev.key == Key.V && ev.type == KeyEventType.KeyDown && ev.isCtrlPressed) -> {
            true
        }

        // pause game
        (ev.key == Key.P && ev.type == KeyEventType.KeyDown && ev.isCtrlPressed) -> {
            AppState.togglePause()
            true
        }

        else -> false
    }
}

val onValueChange = { wordGame: WordGame, text: String ->
    // prevent 'ß' from being converted to 'SS'
    if (!AppState.isPaused()) {
        wordGame.updateWord(
            text.replace(oldChar = 'ß', newChar = '*')
                .uppercase()
                .replace(oldChar = '*', newChar = 'ß')
                .filter { wordGame.isAllowedLetter(it) }
        )
    }
}

val onGameOver = {
    AppState.gameOver()
    AppState.screenState(ScreenType.GameStatistics)
}

@Composable
@Preview
fun WordGame() {
    val wordGame = AppState.newGame()

    backgroundTasks(wordGame) { onGameOver() }

    MaterialTheme {

        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.align(Alignment.Center)) {
                Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    GameField(wordGame.gameField)

                    WordList(wordGame.typedWords.reversed()) { ind: Int, word: Word ->
                        "${wordGame.typedWords.size - ind} - "
                    }
                }

                Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    WordQueue(wordGame.wordQueue)
                }

                Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    LetterChambers(wordGame.letterChambers)
                }

                Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    WordInput(
                        text = wordGame.textInput,
                        word = wordGame.wordInput,
                        onValueChange = { text -> onValueChange(wordGame, text) },
                        onKeyEvent = { ev -> onKeyEvent(wordGame, ev) })
                }

                Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    SimpleButton(text = AppState.translate("main_menu_button")) {
                        AppState.screenState(ScreenType.MainMenu)
                    }
                }
            }
        }
    }
}