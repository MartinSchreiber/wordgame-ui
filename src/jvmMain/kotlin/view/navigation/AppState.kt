package view.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import constants.Language
import constants.Level
import constants.ScreenType
import model.Letter
import model.WordGame
import persistence.PlayerData
import util.LetterUtil

object AppState {
    private var screen: MutableState<ScreenType> = mutableStateOf(ScreenType.PlayerMenu)
    private var language = mutableStateOf(Language.ENGLISH)

    var playerData: PlayerData? = null
    var wordGame: WordGame? = null
    var level: Level = Level.ONE
    var loot: List<Letter> = listOf()

    fun loadPlayerData(playerData: PlayerData) {
        this.playerData = playerData
        language = mutableStateOf(playerData.language)
    }

    fun screenState(): ScreenType = screen.value

    fun screenState(state: ScreenType) {
        screen.value = state
    }

    fun language(): Language = language.value

    fun language(language: Language) {
        this.language.value = language
        this.playerData?.language = language
    }

    fun laboratory(): PlayerData.Laboratory = playerData!!.laboratory[language()]!!

    fun laboratory(laboratory: PlayerData.Laboratory) {
        playerData?.laboratory?.set(language(), laboratory)
    }

    fun loot() {
        loot = LetterUtil.getLootedLetters(level, language())
        playerData?.laboratory?.get(language())?.inactiveLetters?.addAll(loot)
    }
}