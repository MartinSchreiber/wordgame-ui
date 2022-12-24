package view.menu

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import constants.Language
import constants.ScreenType
import view.components.SimpleButton
import view.navigation.AppState

@Composable
fun Settings() {
    Text(text = AppState.translate("change_language"))

    Row {
        Language.values().forEach { language ->
            SimpleButton(text = languageLabel(language)) { AppState.language(language) }
        }
    }

    SimpleButton(text = AppState.translate("main_menu_button")) {
        AppState.playerData?.persist()
        AppState.screenState(ScreenType.MainMenu)
    }
}

fun languageLabel(language: Language): String {
    return if (AppState.language() == language) {
        "[${AppState.translate(language.name)}]"
    } else {
        AppState.translate(language.name)
    }
}