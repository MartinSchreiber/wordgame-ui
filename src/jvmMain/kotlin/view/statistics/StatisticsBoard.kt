package view.statistics

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.onClick
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import constants.ScreenType
import view.components.SimpleButton
import view.navigation.AppState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StatisticsBoard() {
    val gameDataList = AppState.gameDataList()
    val displayedGameData = remember { mutableStateOf(gameDataList.firstOrNull()) }

    Row {
        Column(modifier = Modifier.fillMaxWidth(0.3f)) {
            gameDataList.forEach {
                Text(text = it.timeStamp, modifier = Modifier.onClick { displayedGameData.value = it })
            }
        }

        Column {
            displayedGameData.value?.let {
                GameData(it)
            }
        }
    }

    SimpleButton(text = AppState.translate("main_menu_button")) { AppState.screenState(ScreenType.MainMenu) }
}