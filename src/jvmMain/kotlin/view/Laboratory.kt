package view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import constants.ScreenType
import model.Letter
import util.PersistenceUtil
import view.components.SimpleButton
import view.navigation.AppState

@Composable
fun Laboratory() {
    val laboratory = AppState.laboratory()

    val activeLetters = remember { laboratory.activeLetters.toMutableStateList() }
    val inactiveLetters = remember { laboratory.inactiveLetters.toMutableStateList() }
    val combinationChamber = remember { laboratory.combinationChamber.toMutableStateList() }
    val resultChamber = remember { laboratory.resultChamber.toMutableStateList() }

    Column {
        Row {
            Column(modifier = Modifier.fillMaxWidth(0.5f)) {
                LetterGrid(
                    letters = activeLetters,
                    title = "Active Letters",
                    subTitle = "Left Click: Move to Inactive Letters\nRight Click: Move to Combination Chamber"
                ) { letter: Letter, rightMouseBtn: Boolean ->
                    if (rightMouseBtn) {
                        combinationChamber.add(letter)
                    } else {
                        inactiveLetters.add(letter)
                    }
                }
            }
            Column(modifier = Modifier.fillMaxWidth()) {
                LetterGrid(
                    letters = inactiveLetters,
                    title = "Inactive Letters",
                    subTitle = "Left Click: Move to Active Letters\nRight Click: Move to Combination Chamber"
                ) { letter: Letter, rightMouseBtn: Boolean ->
                    if (rightMouseBtn) {
                        combinationChamber.add(letter)
                    } else {
                        activeLetters.add(letter)
                    }
                }
            }
        }
        Row {
            Column(modifier = Modifier.fillMaxWidth(0.47f)) {
                LetterGrid(
                    letters = combinationChamber,
                    title = "Combination Chamber",
                    subTitle = "Left Click: Move to Inactive Letters\nRight Click: Move to Active Letters"
                ) { letter: Letter, rightMouseBtn: Boolean ->
                    if (rightMouseBtn) {
                        activeLetters.add(letter)
                    } else {
                        inactiveLetters.add(letter)
                    }
                }
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                SimpleButton(onClick = {
                    resultChamber.addAll(combinationChamber)
                    combinationChamber.clear()
                }, text = "=>")
            }
            Column(modifier = Modifier.fillMaxWidth()) {
                LetterGrid(
                    letters = resultChamber,
                    title = "Result Chamber",
                    subTitle = "Left Click: Move to Active Letters\nRight Click: Move to Inactive Letters"
                ) { letter: Letter, rightMouseBtn: Boolean ->
                    if (rightMouseBtn) {
                        inactiveLetters.add(letter)
                    } else {
                        activeLetters.add(letter)
                    }
                }
            }

        }
        SimpleButton(onClick = {
            laboratory.activeLetters = activeLetters.toList()
            laboratory.inactiveLetters = inactiveLetters.toList()
            laboratory.combinationChamber = combinationChamber.toList()
            laboratory.resultChamber = resultChamber.toList()

            AppState.laboratory(laboratory)
            PersistenceUtil.persistPlayer(AppState.playerData!!)

            AppState.screenState(ScreenType.MainMenu)
        }, text = "Main Menu")
    }
}