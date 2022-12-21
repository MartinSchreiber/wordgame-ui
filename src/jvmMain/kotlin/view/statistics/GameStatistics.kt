package view.statistics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import constants.ScreenType
import view.components.SimpleButton
import view.navigation.AppState

@Composable
fun GameStatistics() {
    val gameData = AppState.persistCurrentGameData()

    GameData(gameData)

    Row {
        Column {
            SimpleButton(text = AppState.translate("main_menu_button")) { AppState.screenState(ScreenType.MainMenu) }
        }
        Column {
            SimpleButton(text = AppState.translate("replay_level_button")) { AppState.screenState(ScreenType.WordGame) }
        }
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