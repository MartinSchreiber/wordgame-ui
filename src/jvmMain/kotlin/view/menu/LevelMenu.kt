package view.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import constants.Level
import constants.ScreenType
import view.components.SimpleButton
import view.navigation.AppState

@Composable
fun LevelMenu() {
    Row(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().align(Alignment.CenterVertically)) {
            Level.values().forEach { level ->
                Row {
                    SimpleButton(text = level.toString()) {
                        AppState.level = level
                        AppState.screenState(ScreenType.WordGame)
                    }
                }
            }
        }
    }

}