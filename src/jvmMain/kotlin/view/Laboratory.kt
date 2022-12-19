package view

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import constants.ScreenType
import util.LetterUtil
import util.PersistenceUtil
import view.components.SimpleButton
import view.navigation.AppState

@Composable
fun Laboratory() {
    val laboratory = AppState.laboratory()

    val activeLetters = remember { laboratory.activeLetters.sortedBy { it.level }.toMutableStateList() }
    val inactiveLetters = remember { laboratory.inactiveLetters.sortedBy { it.level }.toMutableStateList() }
    val combinationChamber = remember { laboratory.combinationChamber.sortedBy { it.level }.toMutableStateList() }
    val resultChamber = remember { laboratory.resultChamber.sortedBy { it.level }.toMutableStateList() }

    Column {
        Row {
            Column(modifier = Modifier.fillMaxWidth(0.5f).fillMaxHeight(0.5f).padding(end = 20f.dp)) {
                LetterGrid(
                    letters = activeLetters,
                    title = AppState.translate("active_letters_title"),
                    subTitle = AppState.translate("active_letters_sub_title")
                ) { letterInd: Int, rightMouseBtn: Boolean ->
                    if (activeLetters.size > 12) {
                        if (rightMouseBtn) {
                            combinationChamber.add(activeLetters[letterInd])
                            combinationChamber.sortBy { it.level }
                        } else {
                            inactiveLetters.add(activeLetters[letterInd])
                            inactiveLetters.sortBy { it.level }
                        }
                        activeLetters.removeAt(letterInd)
                    }
                }
            }
            Column(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.5f)) {
                LetterGrid(
                    letters = inactiveLetters,
                    title = AppState.translate("inactive_letters_title"),
                    subTitle = AppState.translate("inactive_letters_sub_title")
                ) { letterInd: Int, rightMouseBtn: Boolean ->
                    if (rightMouseBtn) {
                        combinationChamber.add(inactiveLetters[letterInd])
                        combinationChamber.sortBy { it.level }
                    } else {
                        activeLetters.add(inactiveLetters[letterInd])
                        activeLetters.sortBy { it.level }
                    }
                    inactiveLetters.removeAt(letterInd)
                }
            }
        }
        Row {
            Column(modifier = Modifier.fillMaxWidth(0.47f).fillMaxHeight(0.7f)) {
                LetterGrid(
                    letters = combinationChamber,
                    title = AppState.translate("combination_chamber_title"),
                    subTitle = AppState.translate("combination_chamber_sub_title")
                ) { letterInd: Int, rightMouseBtn: Boolean ->
                    if (rightMouseBtn) {
                        activeLetters.add(combinationChamber[letterInd])
                        activeLetters.sortBy { it.level }
                    } else {
                        inactiveLetters.add(combinationChamber[letterInd])
                        inactiveLetters.sortBy { it.level }
                    }
                    combinationChamber.removeAt(letterInd)
                }
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                SimpleButton(text = "=>") {
                    LetterUtil.combine(combinationChamber, AppState.language())
                        .takeIf { it.isNotEmpty() }
                        ?.let {
                            resultChamber.addAll(it)
                            combinationChamber.clear()
                        }
                }
            }
            Column(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.7f)) {
                LetterGrid(
                    letters = resultChamber,
                    title = AppState.translate("result_chamber_title"),
                    subTitle = AppState.translate("result_chamber_sub_title")
                ) { letterInd: Int, rightMouseBtn: Boolean ->
                    if (rightMouseBtn) {
                        inactiveLetters.add(resultChamber[letterInd])
                        inactiveLetters.sortBy { it.level }
                    } else {
                        activeLetters.add(resultChamber[letterInd])
                        activeLetters.sortBy { it.level }
                    }
                    resultChamber.removeAt(letterInd)
                }
            }

        }
        Row {
            SimpleButton(text = AppState.translate("main_menu_button")) {
                laboratory.activeLetters = activeLetters.toList()
                laboratory.inactiveLetters = inactiveLetters.toMutableList()
                laboratory.combinationChamber = combinationChamber.toList()
                laboratory.resultChamber = resultChamber.toList()

                AppState.laboratory(laboratory)
                PersistenceUtil.persistPlayer(AppState.playerData!!)

                AppState.screenState(ScreenType.MainMenu)
            }
            SimpleButton(text = AppState.translate("letter_overview_button")) { AppState.screenState(ScreenType.LetterOverview) }
        }
    }
}