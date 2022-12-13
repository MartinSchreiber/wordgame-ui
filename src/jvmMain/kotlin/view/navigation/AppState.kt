package view.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import constants.Language
import constants.Level
import constants.ScreenType
import model.WordGame

object AppState {
    private var screen: MutableState<ScreenType> = mutableStateOf(ScreenType.MainMenu)
    private var language = mutableStateOf(Language.ENGLISH)

    var wordGame: WordGame? = null
    var level: Level = Level.ONE

    fun screenState(): ScreenType {
        return screen.value
    }

    fun screenState(state: ScreenType) {
        screen.value = state
    }

    fun language(): Language {
        return language.value
    }

    fun language(language: Language) {
        this.language.value = language
    }
}