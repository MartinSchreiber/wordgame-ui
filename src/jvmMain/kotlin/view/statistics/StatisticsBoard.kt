package view.statistics

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onClick
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import constants.ScreenType
import view.components.ScrollableBox
import view.components.SimpleButton
import view.navigation.AppState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StatisticsBoard() {
    val gameDataList = AppState.gameDataList()
    val displayedGameData = remember { mutableStateOf(gameDataList.firstOrNull()) }

    Row(Modifier.fillMaxHeight(0.9f).fillMaxWidth()) {
        ScrollableBox(modifier = Modifier.fillMaxWidth(0.3f).fillMaxHeight()) {
            gameDataList.forEach {
                val modifier = if (it == displayedGameData.value!!) {
                    Modifier.background(Color.LightGray)
                } else {
                    Modifier
                }

                Text(text = it.timeStamp, modifier = modifier.onClick { displayedGameData.value = it })
            }
        }

        Column(modifier = Modifier.fillMaxSize()) {
            displayedGameData.value?.let {
                GameData(it)
            }
        }
    }

    Row {
        SimpleButton(text = AppState.translate("main_menu_button")) { AppState.screenState(ScreenType.MainMenu) }

        SimpleButton(text = AppState.translate("statistics_overview_button")) { AppState.screenState(ScreenType.StatisticsOverview) }
    }
}