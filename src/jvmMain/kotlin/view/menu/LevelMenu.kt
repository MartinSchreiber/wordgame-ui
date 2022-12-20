package view.menu

import androidx.compose.runtime.Composable
import constants.Level
import constants.ScreenType
import view.components.SimpleButton
import view.navigation.AppState

@Composable
fun LevelMenu() {
    Level.values().forEach { level ->
        SimpleButton(text = (level.ordinal + 1).toString()) {
            AppState.level = level
            AppState.screenState(ScreenType.WordGame)
        }
    }

    SimpleButton(text = AppState.translate("main_menu_button")) { AppState.screenState(ScreenType.MainMenu) }
}