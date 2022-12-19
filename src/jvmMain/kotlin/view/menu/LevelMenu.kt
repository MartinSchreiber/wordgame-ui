package view.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import constants.Level
import constants.ScreenType
import view.components.SimpleButton
import view.navigation.AppState

@Composable
fun LevelMenu() {
    Column(modifier = Modifier.fillMaxSize()) {
        Level.values().forEach { level ->
            Row {
                SimpleButton(text = (level.ordinal + 1).toString()) {
                    AppState.level = level
                    AppState.screenState(ScreenType.WordGame)
                }
            }
        }
        Row {
            SimpleButton(text = AppState.translate("main_menu_button")) { AppState.screenState(ScreenType.MainMenu) }
        }
    }

}