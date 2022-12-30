package view.menu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import constants.ScreenType
import persistence.Converter
import persistence.PlayerData
import persistence.repos.PlayerDataRepo
import view.components.SimpleButton
import view.navigation.AppState


val redirect = { playerData: PlayerData ->
    AppState.playerData(playerData)
    AppState.screenState(ScreenType.MainMenu)
}
val persistAndRedirect = { newPlayerName: String ->
    val playerData = Converter.toPlayerData(name = newPlayerName)
    playerData.persist()
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
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            val newPlayerName = remember { mutableStateOf("") }

            PlayerDataRepo.getList().forEach { playerData ->
                Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    SimpleButton(text = "${playerData.id} - ${playerData.name}") { redirect(playerData) }
                }
            }


            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(text = AppState.translate("new_player_title"), color = Color.White)
            }

            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {

                TextField(
                    value = newPlayerName.value,
                    onValueChange = { newPlayerName.value = it },
                    label = { Text(AppState.translate("new_player_input_label")) },
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.White,
                        placeholderColor = Color.White,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.LightGray
                    ),
                    singleLine = true,
                    modifier = Modifier.onPreviewKeyEvent { ev ->
                        onKeyEvent(newPlayerName.value, ev)
                    }
                )
            }

            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                SimpleButton(text = AppState.translate("add_player_button")) { persistAndRedirect(newPlayerName.value) }
            }
        }
    }

}