package view.laboratory

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import constants.ScreenType
import util.LetterUtil
import view.components.SimpleButton
import view.navigation.AppState

@Composable
fun LetterOverview() {
    //TODO: Add explanation of Letter-Types and combination-logic
    val letterValueMap = LetterUtil.getLetterValueMap(AppState.language())
        .map { group ->
            Pair(group.key, group.value.joinToString(separator = " ") { "(${it.letter})" })
        }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(text = AppState.translate("letter_overview_title"), color = Color.White)
            }

            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Column {
                    letterValueMap.forEach {
                        Text(
                            text = AppState.translate("letter_overview_points_label").format(it.first, it.second),
                            color = Color.White
                        )
                        Text(text = "---", color = Color.White)
                    }
                }
            }

            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                SimpleButton(text = AppState.translate("laboratory_button")) { AppState.screenState(ScreenType.Laboratory) }

                SimpleButton(text = AppState.translate("main_menu_button")) { AppState.screenState(ScreenType.MainMenu) }
            }
        }
    }
}