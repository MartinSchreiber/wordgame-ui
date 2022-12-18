package view

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import constants.ScreenType
import util.LetterUtil
import view.components.SimpleButton
import view.navigation.AppState

@Composable
fun LetterOverview() {
    val letterValueGroups = LetterUtil.getLetterValueGroups(AppState.language())
        .map { group ->
            Pair(group.first, group.second.joinToString(separator = " ") { "($it)" })
        }

    Column {
        Text(text = "Letter Values Overview\n")
        letterValueGroups.forEach {
            Text(text = "${it.first} Points: ${it.second}")
            Text(text = "---")
        }
        SimpleButton(text = "Laboratory") {
            AppState.screenState(ScreenType.Laboratory)
        }
    }

}