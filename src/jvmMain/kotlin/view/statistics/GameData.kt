package view.statistics

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import model.Word
import persistence.GameData
import view.components.WordList
import view.navigation.AppState

@Composable
fun GameData(gameData: GameData) {
    val label = if (gameData.healthRemaining > 0) {
        "stat_level_won"
    } else {
        "stat_level_lost"
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(text = AppState.translate(label).format(gameData.level.ordinal + 1), color = Color.White)
            }
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Column(modifier = Modifier.fillMaxWidth(0.5f)) {
                    Row {
                        WordList(gameData.typedWords.sortedByDescending { it.totalValue() })
                        { _: Int, word: Word -> "${word.totalValue()} - " }
                    }
                }

                Column {
                    Text(
                        text = AppState.translate("stat_total_word_damage").format(gameData.totalWordDamage),
                        color = Color.White
                    )

                    Text(
                        text = AppState.translate("stat_average_word_damage").format(gameData.averageWordDamage),
                        color = Color.White
                    )

                    Text(
                        text = AppState.translate("stat_time_played").format(gameData.playTime / 1000f),
                        color = Color.White
                    )

                    Text(
                        text = AppState.translate("stat_paused_time").format(gameData.pausedTime / 1000f),
                        color = Color.White
                    )

                    Text(
                        text = AppState.translate("stat_letters_per_minute").format(gameData.lettersPerMinute),
                        color = Color.White
                    )

                    Text(
                        text = AppState.translate("stat_enemies_remaining").format(gameData.enemiesRemaining),
                        color = Color.White
                    )

                    Text(
                        text = AppState.translate("stat_health_remaining").format(gameData.healthRemaining),
                        color = Color.White
                    )

                    Text(
                        text = AppState.translate("stat_looted_letters")
                            .format(gameData.lootedLetters.joinToString(" ")), color = Color.White
                    )
                }
            }
        }
    }
}