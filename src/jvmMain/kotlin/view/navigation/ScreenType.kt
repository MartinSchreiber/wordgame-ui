package view.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import constants.Language

enum class ScreenType {
    MainMenu, GameScreen
}

object AppState {
    private var screen: MutableState<ScreenType> = mutableStateOf(ScreenType.MainMenu)

    fun screenState(): ScreenType {
        return screen.value
    }

    fun screenState(state: ScreenType) {
        println("ScreenState ist now: $state")
        screen.value = state
    }
}

object GameSettings {
    private var language = mutableStateOf(Language.ENGLISH)

    fun language(): Language {
        return language.value
    }

    fun language(language: Language) {
        println("Language is now: $language")
        this.language.value = language
    }
}