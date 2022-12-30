package view.statistics

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import constants.ScreenType
import view.components.ScrollableBox
import view.components.SimpleButton
import view.gameplay.WordDisplay
import view.navigation.AppState

@Composable
fun StatisticsData() {
    val statisticsData = remember { mutableStateOf(AppState.statisticsData()) }


    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            statisticsData.value?.let {
                Row(modifier = Modifier.fillMaxHeight(0.3f).fillMaxWidth().align(Alignment.CenterHorizontally)) {
                    Column(modifier = Modifier.fillMaxHeight().fillMaxWidth(0.4f)) {
                        Text(text = AppState.translate("stat_general_title"), color = Color.White)

                        Text(text = "Total Games Played: %d".format(it.totalGamesPlayed), color = Color.White)

                        Text(
                            text = AppState.translate("stat_total_time_played").format(it.totalTimePlayed / 60000f),
                            color = Color.White
                        )

                        Text(
                            text = AppState.translate("stat_total_paused_time").format(it.totalPausedTime / 60000f),
                            color = Color.White
                        )

                        Text(
                            text = AppState.translate("stat_total_word_damage").format(it.totalWordDamage),
                            color = Color.White
                        )

                        Text(
                            text = AppState.translate("stat_average_word_damage").format(it.averageWordDamage),
                            color = Color.White
                        )

                        Text(
                            text = AppState.translate("stat_letters_per_minute").format(it.lettersPerMinute),
                            color = Color.White
                        )
                    }

                    Column(modifier = Modifier.fillMaxSize()) {
                        Text(text = AppState.translate("stat_overview_level_data_title"), color = Color.White)

                        it.levelData.forEach {
                            Text(
                                text = AppState.translate("stat_overview_level_data").format(
                                    it.level.ordinal + 1,
                                    it.min / 60f,
                                    it.max / 60f,
                                    it.avg / 60f,
                                    it.gameNumber
                                ), color = Color.White
                            )
                        }
                    }
                }

                Row(modifier = Modifier.fillMaxHeight(0.9f).fillMaxWidth().align(Alignment.CenterHorizontally)) {
                    ScrollableBox(modifier = Modifier.fillMaxHeight().fillMaxWidth(0.3f)) {
                        Text(text = AppState.translate("stat_overview_frequent_words_title"), color = Color.White)

                        it.wordsByNumber.forEach {
                            Text(text = "${it.second} * ${it.first}", color = Color.White)
                        }
                    }

                    ScrollableBox(modifier = Modifier.fillMaxSize()) {
                        Text(text = AppState.translate("stat_overview_strongest_words_title"), color = Color.White)

                        it.wordsByValue.forEachIndexed { ind, word ->
                            Row {
                                Text(text = "${ind + 1}(${word.totalValue()}) - ", color = Color.White)
                                WordDisplay(word)
                            }
                        }
                    }
                }
            }

            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                SimpleButton(text = AppState.translate("statistics_board_button")) { AppState.screenState(ScreenType.StatisticsBoard) }

                SimpleButton(text = AppState.translate("main_menu_button")) { AppState.screenState(ScreenType.MainMenu) }

                SimpleButton(text = AppState.translate("stat_overview_generate_button")) {
                    statisticsData.value = AppState.generateStatisticsData()
                }
            }
        }
    }
}