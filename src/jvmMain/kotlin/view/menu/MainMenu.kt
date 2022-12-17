package view.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import constants.ScreenType
import view.components.SimpleButton
import view.navigation.AppState

@Composable
fun MainMenu() {
    Row(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().align(Alignment.CenterVertically)) {
            AppState.playerData?.let { playerData ->
                Text("Greetings ${playerData.name}!")
            }
            SimpleButton(text = "Select Level!") { AppState.screenState(ScreenType.LevelMenu) }
            SimpleButton(text = "To The Laboratory!") { AppState.screenState(ScreenType.Laboratory) }
            SimpleButton(text = "Settings") { AppState.screenState(ScreenType.Settings) }
            SimpleButton(text = "Player Menu") { AppState.screenState(ScreenType.PlayerMenu) }
        }
    }

}