package view.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import constants.Language
import constants.Level
import constants.ScreenType
import model.Letter
import model.WordGame
import persistence.Converter
import persistence.GameData
import persistence.PlayerData
import persistence.StatisticsData
import persistence.repos.AbstractJsonRepo
import persistence.repos.GameDataRepo
import persistence.repos.StatisticsDataRepo
import util.LanguageUtil
import util.LetterUtil

object AppState {
    private var screen: MutableState<ScreenType> = mutableStateOf(ScreenType.PlayerMenu)
    private var language = mutableStateOf(Language.ENGLISH)
    private var paused = mutableStateOf(false)

    private var languageUtil = LanguageUtil(language())
    private var wordGame: WordGame? = null
    private var loot: List<Letter> = listOf()

    private var gameDataList: MutableList<GameData> = mutableListOf()
    private var statisticsData: StatisticsData? = null

    var playerData: PlayerData? = null
    var level: Level = Level.L1

    init {
        AbstractJsonRepo.initDir()
    }

    // access to vars
    fun screenState(): ScreenType = screen.value

    fun screenState(state: ScreenType) {
        screen.value = state
    }

    fun language(): Language = language.value

    fun language(language: Language) {
        this.language.value = language

        playerData?.language = language
        languageUtil = LanguageUtil(language)

        statisticsData = null
    }

    fun isPaused(): Boolean = paused.value

    fun togglePause() {
        if (!paused.value) {
            wordGame!!.startPause = System.currentTimeMillis()
        } else {
            wordGame!!.totalPauseTime += System.currentTimeMillis() - wordGame!!.startPause!!
            wordGame!!.startPause = null
        }

        paused.value = !paused.value
    }

    fun laboratory(): PlayerData.Laboratory = playerData!!.laboratory[language()]!!
    fun laboratory(laboratory: PlayerData.Laboratory) = playerData?.laboratory?.set(language(), laboratory)

    fun playerData(playerData: PlayerData) {
        this.playerData = playerData
        language(playerData.language)
    }

    // access to virtual vars
    fun gameDataList(): List<GameData> {
        if (gameDataList.isEmpty()) {
            gameDataList = GameDataRepo.getList(playerData!!.id, language()).toMutableList()
        }
        return gameDataList
    }

    fun gameData(): GameData {
        val gameData = Converter.toGameData(wordGame!!, loot)
        gameData.persist(playerData!!.id, language())
        return gameData
    }

    fun statisticsData(): StatisticsData? {
        if (statisticsData == null) {
            statisticsData = StatisticsDataRepo.getList(playerData!!.id, language()).firstOrNull()
        }

        if (statisticsData == null) {
            generateStatisticsData()
        }
        return statisticsData
    }

    fun generateStatisticsData(): StatisticsData? {
        if (gameDataList().isNotEmpty()) {
            statisticsData = Converter.toStatisticsData(gameDataList())

            statisticsData!!.persist(playerId = playerData!!.id, language = language())
        }
        return statisticsData
    }

    // game-functions
    fun newGame(): WordGame {
        wordGame = WordGame(
            language = language(), level = level, specialLetters = laboratory().activeLetters.shuffled()
        )
        paused.value = false

        return wordGame!!
    }

    fun gameOver() {
        wordGame!!.endTime = System.currentTimeMillis()
        loot = LetterUtil.getLootedLetters(level, language())

        playerData?.laboratory?.get(language())?.inactiveLetters?.addAll(loot)
        playerData?.persist()
    }

    // ui-functions
    fun translate(label: String) = languageUtil.translate(label)
}