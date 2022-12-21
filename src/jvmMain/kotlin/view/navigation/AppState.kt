package view.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import constants.Language
import constants.Level
import constants.ScreenType
import model.Letter
import model.WordGame
import persistence.GameData
import persistence.PlayerData
import util.LanguageUtil
import util.LetterUtil
import util.PersistenceUtil

object AppState {
    private var screen: MutableState<ScreenType> = mutableStateOf(ScreenType.PlayerMenu)
    private var language = mutableStateOf(Language.ENGLISH)

    var languageUtil = LanguageUtil(language())
    var playerData: PlayerData? = null
    var wordGame: WordGame? = null
    var level: Level = Level.L1
    var loot: List<Letter> = listOf()

    fun loadPlayerData(playerData: PlayerData) {
        this.playerData = playerData
        language(playerData.language)
    }

    fun screenState(): ScreenType = screen.value

    fun screenState(state: ScreenType) {
        screen.value = state
    }

    fun language(): Language = language.value

    fun language(language: Language) {
        this.language.value = language

        playerData?.language = language
        languageUtil = LanguageUtil(language)
    }

    fun laboratory(): PlayerData.Laboratory = playerData!!.laboratory[language()]!!
    fun laboratory(laboratory: PlayerData.Laboratory) = playerData?.laboratory?.set(language(), laboratory)

    fun newGame(): WordGame {
        wordGame = WordGame(
            language = language(),
            level = level,
            specialLetters = laboratory().activeLetters.shuffled()
        )
        return wordGame!!
    }

    fun loot() {
        loot = LetterUtil.getLootedLetters(level, language())
        playerData?.laboratory?.get(language())?.inactiveLetters?.addAll(loot)
    }

    fun gameDataList(): List<GameData> = PersistenceUtil.getGameData(playerData!!.id, language())

    fun persistCurrentGameData(): GameData =
        PersistenceUtil.persistGameData(
            wordGame = wordGame!!,
            lootedLetters = loot,
            playerId = playerData!!.id
        )

    fun translate(label: String) = languageUtil.translate(label)
}