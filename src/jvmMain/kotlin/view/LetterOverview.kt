package view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import constants.ScreenType
import util.LetterUtil
import view.components.SimpleButton
import view.navigation.AppState

@Composable
fun LetterOverview() {
    //TODO: Add explanation of Letter-Types and combination-logic
    val letterValueGroups = LetterUtil.getLetterValueGroups(AppState.language())
        .map { group ->
            Pair(group.first, group.second.joinToString(separator = " ") { "($it)" })
        }

    Text(text = AppState.translate("letter_overview_title"))
    letterValueGroups.forEach {
        Text(text = AppState.translate("letter_overview_points_label").format(it.first, it.second))
        Text(text = "---")
    }
    SimpleButton(text = AppState.translate("laboratory_button")) {
        AppState.screenState(ScreenType.Laboratory)
    }

}