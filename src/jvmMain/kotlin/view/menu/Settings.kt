package view.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import constants.Language
import constants.ScreenType
import util.PersistenceUtil
import view.components.SimpleButton
import view.navigation.AppState

@Composable
fun Settings() {
    val languageLabel = { language: Language ->
        if (AppState.language() == language) {
            "[$language]"
        } else {
            "$language"
        }
    }

    Column {
        Text(text = "Change Language")
        Row {
            Language.values().forEach { language ->
                SimpleButton(text = languageLabel(language)) { AppState.language(language) }
            }
        }
        SimpleButton(text = "Main Menu") {
            PersistenceUtil.persistPlayer(AppState.playerData!!)
            AppState.screenState(ScreenType.MainMenu)
        }
    }
}