package view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import constants.ScreenType
import view.components.SimpleButton
import view.navigation.AppState
import view.statistics.TypedWords

@Composable
fun GameStatistics() {
    val wordGame = AppState.wordGame!!
    val timePlayed = (wordGame.endTime!! - wordGame.startTime) / 1000
    val lettersPerMinute = wordGame.typedWords.sumOf { it.size() } / (timePlayed / 60)
    val totalWordDamage = wordGame.typedWords.sumOf { it.getTotalValue().toDouble() }
    val averageWordDamage = totalWordDamage / wordGame.typedWords.size
    Row {
        Column {
            Row {
                TypedWords(wordGame.typedWords)
            }
            Row {
                if (wordGame.gameField.path.base.health.value > 0) {
                    Text(text = "Level ${wordGame.level} Won!")
                } else {
                    Text(text = "Level ${wordGame.level} Lost!")
                }
            }
            Row {
                Text(text = "Language: ${wordGame.language}")
            }
        }
        Column {
            Row {
                Text(text = "Total Word-Damage: $totalWordDamage")
            }
            Row {
                Text(text = "Average Word-Damage: $averageWordDamage")
            }
            Row {
                Text(text = "Time Played (Seconds): $timePlayed")
            }
            Row {
                Text(text = "Letters per Minute: $lettersPerMinute")
            }
            Row {
                Text(text = "Enemies remaining: ${wordGame.gameField.enemiesOnField.size}(${wordGame.gameField.enemiesIncoming.size})")
            }
            Row {
                Text(text = "Health remaining: ${wordGame.gameField.path.base.health.value}")
            }
        }
    }
    Row {
        SimpleButton(
            onClick = { AppState.screenState(ScreenType.MainMenu) }, text = "Menu"
        )
        //TODO: continue with next level
    }
}