package view.statistics

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import constants.ScreenType
import view.components.ScrollableBox
import view.components.SimpleButton
import view.navigation.AppState

@Composable
fun StatisticsData() {
    val statisticsData = remember { mutableStateOf(AppState.statisticsData()) }

    statisticsData.value?.let {
        Row(modifier = Modifier.fillMaxHeight(0.4f).fillMaxWidth()) {
            Column(modifier = Modifier.fillMaxHeight().fillMaxWidth(0.3f)) {
                Text(text = AppState.translate("stat_general_title"))

                Text(text = "Total Games Played: %d".format(it.totalGamesPlayed))

                Text(text = AppState.translate("stat_total_word_damage").format(it.totalWordDamage))

                Text(text = AppState.translate("stat_average_word_damage").format(it.averageWordDamage))

                Text(text = AppState.translate("stat_letters_per_minute").format(it.lettersPerMinute))
            }

            Column(modifier = Modifier.fillMaxSize()) {
                Text(text = AppState.translate("stat_overview_level_data_title"))

                it.levelData.forEach {
                    Text(
                        text = AppState.translate("stat_overview_level_data").format(
                            it.level.ordinal + 1,
                            it.min / 60f,
                            it.max / 60f,
                            it.avg / 60f,
                            it.gameNumber
                        )
                    )
                }
            }
        }

        Row(modifier = Modifier.fillMaxHeight(0.8f).fillMaxWidth()) {
            ScrollableBox(modifier = Modifier.fillMaxHeight().fillMaxWidth(0.5f)) {
                Text(text = AppState.translate("stat_overview_frequent_words_title"))

                it.wordsByNumber.forEach {
                    Text(text = "${it.second} * ${it.first}")
                }
            }

            ScrollableBox(modifier = Modifier.fillMaxSize()) {
                Text(text = AppState.translate("stat_overview_strongest_words_title"))

                it.wordsByValue.forEachIndexed { ind, word ->
                    Text(text = "${ind + 1} - $word (${word.totalValue()})")
                }
            }
        }
    }



    Row {
        SimpleButton(text = AppState.translate("statistics_board_button")) { AppState.screenState(ScreenType.StatisticsBoard) }

        SimpleButton(text = AppState.translate("main_menu_button")) { AppState.screenState(ScreenType.MainMenu) }

        SimpleButton(text = AppState.translate("stat_overview_generate_button")) {
            statisticsData.value = AppState.generateStatisticsData()
        }
    }
}