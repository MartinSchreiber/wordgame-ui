package view.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import constants.ScreenType
import view.gameplay.WordGame
import view.laboratory.Laboratory
import view.laboratory.LetterOverview
import view.menu.LevelMenu
import view.menu.MainMenu
import view.menu.PlayerMenu
import view.menu.Settings
import view.statistics.GameStatistics
import view.statistics.StatisticsBoard
import view.statistics.StatisticsData
import view.test.Test

@Composable
fun AppUI() {

    Box(
        modifier = Modifier.fillMaxSize().background(color = Color.DarkGray)
    ) {
        when (AppState.screenState()) {
            ScreenType.MainMenu -> MainMenu()

            ScreenType.PlayerMenu -> PlayerMenu()

            ScreenType.LevelMenu -> LevelMenu()

            ScreenType.Settings -> Settings()

            ScreenType.WordGame -> WordGame()

            ScreenType.GameStatistics -> GameStatistics()

            ScreenType.StatisticsBoard -> StatisticsBoard()

            ScreenType.StatisticsOverview -> StatisticsData()

            ScreenType.Laboratory -> Laboratory()

            ScreenType.LetterOverview -> LetterOverview()

            ScreenType.Test -> Test()
        }
    }
}