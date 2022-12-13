package view.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import control.backgroundTasks
import model.WordGame
import view.WordGame
import view.menu.MainMenu

@Composable
fun AppUI() {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        when (AppState.screenState()) {
            ScreenType.MainMenu -> {
                MainMenu()
            }

            ScreenType.GameScreen -> {
                WordGame(language = GameSettings.language())
                    .let {
                        backgroundTasks(it, onGameOver = { AppState.screenState(ScreenType.MainMenu) })
                        WordGame(it)
                    }
            }
        }
    }
}