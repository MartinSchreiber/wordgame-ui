package view

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import constants.ScreenType
import view.navigation.AppState

@Composable
fun GameStatistics() {
    //TODO
    /* Show Game-Statistics
     * Words-Typed (Letters, Total Values)
     * Total Damage
     * Monsters Defeated
     * Letters per Minute
     * Average Word Damage
     *
     *
    * */
    Row {
        TextButton(onClick = {
            AppState.screenState(ScreenType.MainMenu)
        }) {
            Text("Menu")
        }
    }
}