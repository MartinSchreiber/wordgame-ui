package view.laboratory

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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

    Text(text = AppState.translate("letter_overview_title"))
    letterValueMap.forEach {
        Text(text = AppState.translate("letter_overview_points_label").format(it.first, it.second))
        Text(text = "---")
    }
    Row {
        SimpleButton(text = AppState.translate("laboratory_button")) {
            AppState.screenState(ScreenType.Laboratory)
        }
        SimpleButton(text = AppState.translate("main_menu_button")) {
            AppState.screenState(ScreenType.MainMenu)
        }
    }

}