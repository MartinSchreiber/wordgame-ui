package view.statistics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import constants.ScreenType
import util.PersistenceUtil
import view.components.SimpleButton
import view.navigation.AppState

@Composable
fun GameStatistics() {
    val gameData = PersistenceUtil.persistGameData(
        wordGame = AppState.wordGame!!,
        lootedLetters = AppState.loot,
        playerId = AppState.playerData!!.id
    )
    Row {
        Column {
            Row {
                TypedWords(gameData.typedWords)
            }
            Row {
                if (gameData.healthRemaining > 0) {
                    Text(text = AppState.translate("stat_level_won").format(gameData.level.ordinal + 1))
                } else {
                    Text(text = AppState.translate("stat_level_lost").format(gameData.level.ordinal + 1))
                }
            }
            Row {
                Text(text = AppState.translate("stat_language").format(AppState.translate(gameData.language.name)))
            }
        }
        Column {
            Row {
                Text(text = AppState.translate("stat_total_word_damage").format(gameData.totalWordDamage))
            }
            Row {
                Text(text = AppState.translate("stat_average_word_damage").format(gameData.averageWordDamage))
            }
            Row {
                Text(text = AppState.translate("stat_time_played").format(gameData.playTime / 1000f))
            }
            Row {
                Text(text = AppState.translate("stat_letters_per_minute").format(gameData.lettersPerMinute))
            }
            Row {
                Text(text = AppState.translate("stat_enemies_remaining").format(gameData.enemiesRemaining))
            }
            Row {
                Text(text = AppState.translate("stat_health_remaining").format(gameData.healthRemaining))
            }
            Row {
                Text(text = AppState.translate("stat_looted_letters").format(AppState.loot.joinToString(" ")))
            }
        }
    }
    Row {
        Column {
            SimpleButton(text = AppState.translate("main_menu_button")) { AppState.screenState(ScreenType.MainMenu) }
        }
        Column {
            SimpleButton(text = AppState.translate("replay_level_button")) { AppState.screenState(ScreenType.WordGame) }
        }
        if (gameData.level.hasNext()) {
            Column {
                SimpleButton(text = AppState.translate("next_level_button")) {
                    AppState.level = gameData.level.getNext()
                    AppState.screenState(ScreenType.WordGame)
                }
            }
        }
    }
}