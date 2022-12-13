package view.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import constants.Language
import view.navigation.AppState
import view.navigation.GameSettings
import view.navigation.ScreenType

@Composable
fun MainMenu() {
    Row(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().align(Alignment.CenterVertically)) {
            Row {
                TextButton(onClick = {
                    AppState.screenState(ScreenType.GameScreen)
                }) {
                    Text("Start Game!")
                }
            }
            Row {
                Text(text = "Language")
            }
            Language.values().forEach { language ->
                Row {
                    TextButton(onClick = {
                        GameSettings.language(language)
                    }) {
                        Text(language.toString())
                    }
                }
            }

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