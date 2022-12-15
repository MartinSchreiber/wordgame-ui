package util

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import model.WordGame
import persistence.GameData
import persistence.PlayerData
import view.navigation.AppState
import java.io.File

class PersistenceUtil {
    companion object {
        private const val BASE_PATH = "./wordGameData"
        private const val PLAYER_DATA_FILE = "$BASE_PATH/playerData.json"
        private const val GAME_DATA_PATH = "$BASE_PATH/gameData%d.json"
        private val GSON = GsonBuilder().create()
        private inline fun <reified T> genericType() = object : TypeToken<T>() {}.type
        fun initDir() {
            File("./wordGameData").mkdir()
        }

        fun getPlayerData(): List<PlayerData> {
            val file = File(PLAYER_DATA_FILE)
            if (file.isFile) {
                return GSON.fromJson(file.readText(), genericType<List<PlayerData>>())
            }
            return listOf()
        }

        fun getGameData(playerId: Int): List<GameData> {
            val file = File(GAME_DATA_PATH.format(playerId))
            if (file.isFile) {
                return GSON.fromJson(file.readText(), genericType<List<GameData>>())
            }
            return listOf()
        }

        fun persistPlayer(name: String): PlayerData {
            val playerData = createPlayer(name)
            persistPlayer(playerData)
            return playerData
        }

        fun persistPlayer(playerData: PlayerData) {
            val playerDataList = getPlayerData().toMutableList()
            playerDataList.removeIf { it.id == playerData.id }
            playerDataList.add(playerData)
            File(PLAYER_DATA_FILE).writeText(GSON.toJson(playerDataList))
        }

        fun persistGameData(wordGame: WordGame, playerId: Int): GameData {
            val gameData = convertWordGame(wordGame)
            persistGameData(gameData, playerId)
            return gameData
        }

        private fun persistGameData(gameData: GameData, playerId: Int) {
            val gameDataList = getGameData(playerId).toMutableList()
            gameDataList.add(gameData)
            File(GAME_DATA_PATH.format(playerId)).writeText(GSON.toJson(gameDataList))
        }

        private fun createPlayer(name: String): PlayerData {
            return PlayerData(
                id = getPlayerData().size + 1,
                name = name,
                language = AppState.language(),
                specialLetters = LetterUtil.getSpecialLetterMap()
            )
        }

        private fun convertWordGame(wordGame: WordGame): GameData {
            return GameData(
                level = wordGame.level,
                language = wordGame.language,
                typedWords = wordGame.typedWords.toList(),
                playTime = (wordGame.endTime!! - wordGame.startTime),
                healthRemaining = wordGame.gameField.path.base.health.value,
                enemiesRemaining = wordGame.gameField.enemiesOnField.size + wordGame.gameField.enemiesIncoming.size //TODO move calculations into WordGame-class?
            )
        }
    }

}