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
import constants.Language
import constants.ScreenType
import view.components.SimpleButton
import view.navigation.AppState

@Composable
fun Settings() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(text = AppState.translate("change_language"), color = Color.White)
            }

            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Language.values().forEach { language ->
                    SimpleButton(text = languageLabel(language)) { AppState.language(language) }
                }
            }

            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                SimpleButton(text = AppState.translate("main_menu_button")) {
                    AppState.playerData?.persist()
                    AppState.screenState(ScreenType.MainMenu)
                }
            }
        }
    }
}

fun languageLabel(language: Language): String {
    return if (AppState.language() == language) {
        "[${AppState.translate(language.name)}]"
    } else {
        AppState.translate(language.name)
    }
}