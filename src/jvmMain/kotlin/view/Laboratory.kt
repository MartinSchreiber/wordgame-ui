package view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import constants.ScreenType
import util.PersistenceUtil
import view.components.SimpleButton
import view.navigation.AppState

@Composable
fun Laboratory() {
    Column {
        Row {

        }
        Row {

        }
        SimpleButton(onClick = {
            PersistenceUtil.persistPlayer(AppState.playerData!!)
            AppState.screenState(ScreenType.MainMenu)
        }, text = "Main Menu")
    }
}