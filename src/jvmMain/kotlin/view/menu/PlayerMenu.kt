package view.menu

import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.*
import constants.ScreenType
import persistence.PlayerData
import util.PersistenceUtil
import view.components.SimpleButton
import view.navigation.AppState


val redirect = { playerData: PlayerData ->
    AppState.loadPlayerData(playerData)
    AppState.screenState(ScreenType.MainMenu)
}
val persistAndRedirect = { newPlayerName: String ->
    val playerData = PersistenceUtil.persistPlayer(newPlayerName)
    redirect(playerData)
}

@OptIn(ExperimentalComposeUiApi::class)
val onKeyEvent = { newPlayerName: String, ev: KeyEvent ->
    when {
        (ev.key == Key.Enter && ev.type == KeyEventType.KeyDown) -> {
            persistAndRedirect(newPlayerName)
            true
        }

        else -> false
    }
}

@Composable
fun PlayerMenu() {
    val newPlayerName = remember { mutableStateOf("") }

    PersistenceUtil.getPlayerData().forEach { playerData ->
        SimpleButton(text = playerData.name) { redirect(playerData) }
    }

    Text(text = AppState.translate("new_player_title"))

    TextField(
        value = newPlayerName.value,
        onValueChange = { newPlayerName.value = it },
        label = { Text(AppState.translate("new_player_input_label")) },
        singleLine = true,
        modifier = Modifier.onPreviewKeyEvent { ev ->
            onKeyEvent(newPlayerName.value, ev)
        }
    )

    SimpleButton(text = AppState.translate("add_player_button")) { persistAndRedirect(newPlayerName.value) }
}