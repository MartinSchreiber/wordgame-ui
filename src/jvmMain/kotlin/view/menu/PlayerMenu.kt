package view.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
    Row(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().align(Alignment.CenterVertically)) {
            PersistenceUtil.getPlayerData().forEach { playerData ->
                Row {
                    SimpleButton(text = playerData.name) { redirect(playerData) }
                }
            }
            Row {
                Text(text = "New Player")
            }
            Row {
                TextField(
                    value = newPlayerName.value,
                    onValueChange = { newPlayerName.value = it },
                    label = { Text("Enter Name") },
                    singleLine = true,
                    modifier = Modifier.onPreviewKeyEvent { ev ->
                        onKeyEvent(
                            newPlayerName.value,
                            ev
                        )
                    }
                )
            }
            Row {
                SimpleButton(text = "Add Player") { persistAndRedirect(newPlayerName.value) }
            }
        }
    }
}