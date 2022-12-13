package view.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import constants.Level
import constants.ScreenType
import view.navigation.AppState

@Composable
fun LevelMenu() {
    Row(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().align(Alignment.CenterVertically)) {
            Level.values().forEach { level ->
                Row {
                    Button(onClick = {
                        AppState.level = level
                        AppState.screenState(ScreenType.WordGame)
                    }) {
                        Text(level.toString())
                    }
                }
            }
        }
    }

}