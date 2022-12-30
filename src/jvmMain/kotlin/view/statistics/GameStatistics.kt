package view.statistics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import constants.ScreenType
import view.components.SimpleButton
import view.navigation.AppState

@Composable
fun GameStatistics(modifier: Modifier = Modifier) {
    val gameData = AppState.gameData()

    Column(modifier = modifier) {
        Row(modifier = Modifier.align(Alignment.CenterHorizontally).fillMaxHeight(0.9f)) {
            GameData(gameData)
        }

        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            SimpleButton(text = AppState.translate("main_menu_button")) { AppState.screenState(ScreenType.MainMenu) }

            SimpleButton(text = AppState.translate("replay_level_button")) { AppState.screenState(ScreenType.WordGame) }

            if (gameData.level.hasNext()) {
                Column {
                    SimpleButton(text = AppState.translate("next_level_button")) {
                        AppState.level = gameData.level.getNext()
                        AppState.screenState(ScreenType.WordGame)
                    }
                }
            }
        }
    }
}