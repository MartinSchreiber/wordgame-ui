package view.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
            ScreenType.MainMenu -> MainMenu(modifier = Modifier.align(Alignment.Center))

            ScreenType.PlayerMenu -> PlayerMenu(modifier = Modifier.align(Alignment.Center))

            ScreenType.LevelMenu -> LevelMenu(modifier = Modifier.align(Alignment.Center))

            ScreenType.Settings -> Settings(modifier = Modifier.align(Alignment.Center))

            ScreenType.WordGame -> WordGame(modifier = Modifier.align(Alignment.Center))

            ScreenType.GameStatistics -> GameStatistics(modifier = Modifier.align(Alignment.Center))

            ScreenType.StatisticsBoard -> StatisticsBoard(modifier = Modifier.align(Alignment.Center))

            ScreenType.StatisticsOverview -> StatisticsData(modifier = Modifier.align(Alignment.Center))

            ScreenType.Laboratory -> Laboratory(modifier = Modifier.align(Alignment.Center))

            ScreenType.LetterOverview -> LetterOverview(modifier = Modifier.align(Alignment.Center))

            ScreenType.Test -> Test()
        }
    }
}