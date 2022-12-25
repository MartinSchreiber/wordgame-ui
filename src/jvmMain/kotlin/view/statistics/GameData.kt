package view.statistics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import model.Word
import persistence.GameData
import view.components.WordList
import view.navigation.AppState

@Composable
fun GameData(gameData: GameData) {
    Row(Modifier.fillMaxWidth()) {
        val label = if (gameData.healthRemaining > 0) {
            "stat_level_won"
        } else {
            "stat_level_lost"
        }
        Text(text = AppState.translate(label).format(gameData.level.ordinal + 1))
    }
    Row {
        Column(modifier = Modifier.fillMaxWidth(0.5f)) {
            Row {
                WordList(gameData.typedWords.sortedByDescending { it.totalValue() })
                { _: Int, word: Word -> "${word.totalValue()} - $word" }
            }
        }

        Column {
            Text(text = AppState.translate("stat_total_word_damage").format(gameData.totalWordDamage))

            Text(text = AppState.translate("stat_average_word_damage").format(gameData.averageWordDamage))

            Text(text = AppState.translate("stat_time_played").format(gameData.playTime / 1000f))

            Text(text = AppState.translate("stat_letters_per_minute").format(gameData.lettersPerMinute))

            Text(text = AppState.translate("stat_enemies_remaining").format(gameData.enemiesRemaining))

            Text(text = AppState.translate("stat_health_remaining").format(gameData.healthRemaining))

            Text(text = AppState.translate("stat_looted_letters").format(gameData.lootedLetters.joinToString(" ")))
        }
    }
}