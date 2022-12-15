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
    Column {
        Text(text = "Change Language")
        Row {
            Language.values().forEach { language ->
                SimpleButton(
                    onClick = { AppState.language(language) },
                    text = if (AppState.language() == language) {
                        "[$language]"
                    } else {
                        "$language"
                    }
                )
            }
        }
        SimpleButton(onClick = {
            PersistenceUtil.persistPlayer(AppState.playerData!!)
            AppState.screenState(ScreenType.MainMenu)
        }, text = "Main Menu")
    }
}