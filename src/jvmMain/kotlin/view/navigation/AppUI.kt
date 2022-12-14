package view.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import constants.ScreenType
import view.GameStatistics
import view.WordGame
import view.menu.LevelMenu
import view.menu.MainMenu
import view.menu.PlayerMenu

@Composable
fun AppUI() {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        when (AppState.screenState()) {
            ScreenType.MainMenu -> {
                MainMenu()
            }

            ScreenType.PlayerMenu -> {
                PlayerMenu()
            }

            ScreenType.LevelMenu -> {
                LevelMenu()
            }

            ScreenType.WordGame -> {
                WordGame()
            }

            ScreenType.GameStatistics -> {
                GameStatistics()
            }
        }
    }
}