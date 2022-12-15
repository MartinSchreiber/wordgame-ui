package view.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import constants.Language
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
            Row {
                SimpleButton(
                    onClick = { AppState.screenState(ScreenType.LevelMenu) },
                    text = "Select Level!"
                )
            }
            Row {
                SimpleButton(
                    onClick = { AppState.screenState(ScreenType.PlayerMenu) },
                    text = "Player Menu"
                )
            }
            Row {
                Text(text = "Change Language")
            }
            Language.values().forEach { language ->
                Row {
                    SimpleButton(
                        onClick = { AppState.language(language) },
                        text = language.toString()
                    )
                }
            }

            //TODO implement drop-down
//            Row {
//                DropdownMenu(expanded = true, onDismissRequest = {}) {
//                    Language.values().forEach { language ->
//                        DropdownMenuItem(
//                            onClick = { GameSettings.language(language) }) {
//                            Text(text = language.toString())
//                        }
//                    }
//                }
//            }
        }
    }

}