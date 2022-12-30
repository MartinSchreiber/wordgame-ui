package view.menu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import constants.ScreenType
import view.components.SimpleButton
import view.navigation.AppState

@Composable
fun MainMenu() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                AppState.playerData?.let { playerData ->
                    Text(text = AppState.translate("main_menu_greeting").format(playerData.name), color = Color.White)
                }
            }

            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                SimpleButton(text = AppState.translate("level_menu_button")) { AppState.screenState(ScreenType.LevelMenu) }
            }

            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                SimpleButton(text = AppState.translate("laboratory_button")) { AppState.screenState(ScreenType.Laboratory) }
            }

            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                SimpleButton(text = AppState.translate("statistics_board_button")) { AppState.screenState(ScreenType.StatisticsBoard) }
            }

            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                SimpleButton(text = AppState.translate("setting_button")) { AppState.screenState(ScreenType.Settings) }
            }

            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                SimpleButton(text = AppState.translate("player_menu_button")) { AppState.screenState(ScreenType.PlayerMenu) }
            }
        }
    }

}