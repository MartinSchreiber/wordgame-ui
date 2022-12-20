package view.menu

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import constants.ScreenType
import view.components.SimpleButton
import view.navigation.AppState

@Composable
fun MainMenu() {
    AppState.playerData?.let { playerData ->
        Text(text = AppState.translate("main_menu_greeting").format(playerData.name))
    }

    SimpleButton(text = AppState.translate("level_menu_button")) { AppState.screenState(ScreenType.LevelMenu) }
    SimpleButton(text = AppState.translate("laboratory_button")) { AppState.screenState(ScreenType.Laboratory) }
    SimpleButton(text = AppState.translate("setting_button")) { AppState.screenState(ScreenType.Settings) }
    SimpleButton(text = AppState.translate("player_menu_button")) { AppState.screenState(ScreenType.PlayerMenu) }
}