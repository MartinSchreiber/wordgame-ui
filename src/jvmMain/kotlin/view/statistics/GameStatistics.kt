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
    val gameData = PersistenceUtil.persistGameData(AppState.wordGame!!, AppState.playerData!!.id)
    Row {
        Column {
            Row {
                TypedWords(gameData.typedWords)
            }
            Row {
                if (gameData.healthRemaining > 0) {
                    Text(text = "Level ${gameData.level} Won!")
                } else {
                    Text(text = "Level ${gameData.level} Lost!")
                }
            }
            Row {
                Text(text = "Language: ${gameData.language}")
            }
        }
        Column {
            Row {
                Text(text = "Total Word-Damage: ${gameData.totalWordDamage}")
            }
            Row {
                Text(text = "Average Word-Damage: ${gameData.averageWordDamage}")
            }
            Row {
                Text(text = "Time Played (Seconds): ${gameData.playTime / 1000f}")
            }
            Row {
                Text(text = "Letters per Minute: ${gameData.lettersPerMinute}")
            }
            Row {
                Text(text = "Enemies remaining: ${gameData.enemiesRemaining}")
            }
            Row {
                Text(text = "Health remaining: ${gameData.healthRemaining}")
            }
        }
    }
    Row {
        Column {
            SimpleButton(text = "Menu") { AppState.screenState(ScreenType.MainMenu) }
        }
        Column {
            SimpleButton(text = "Replay Level") { AppState.screenState(ScreenType.WordGame) }
        }
        if (gameData.level.hasNext()) {
            Column {
                SimpleButton(text = "Next Level") {
                    AppState.level = gameData.level.getNext()
                    AppState.screenState(ScreenType.WordGame)
                }
            }
        }
    }
}