package view.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import constants.ScreenType
import view.Laboratory
import view.LetterOverview
import view.gameplay.WordGame
import view.menu.LevelMenu
import view.menu.MainMenu
import view.menu.PlayerMenu
import view.menu.Settings
import view.statistics.GameStatistics
import view.statistics.StatisticsBoard

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

            ScreenType.Settings -> {
                Settings()
            }

            ScreenType.WordGame -> {
                WordGame()
            }

            ScreenType.GameStatistics -> {
                GameStatistics()
            }

            ScreenType.Laboratory -> {
                Laboratory()
            }

            ScreenType.LetterOverview -> {
                LetterOverview()
            }

            ScreenType.StatisticsBoard -> {
                StatisticsBoard()
            }
        }
    }
}