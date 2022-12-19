package util

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import model.Letter
import model.WordGame
import persistence.GameData
import persistence.PlayerData
import view.navigation.AppState
import java.io.File
import java.time.LocalDateTime

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

        fun persistGameData(wordGame: WordGame, lootedLetters: List<Letter>, playerId: Int): GameData {
            val gameData = convertWordGame(wordGame, lootedLetters)
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

        private fun convertWordGame(wordGame: WordGame, lootedLetters: List<Letter>): GameData {
            val playTime = (wordGame.endTime!! - wordGame.startTime)
            val lettersPerMinute = wordGame.typedWords.sumOf { it.size() } / (playTime / 60000f)
            val totalWordDamage = wordGame.typedWords.sumOf { it.getTotalValue().toDouble() }.toFloat()
            val averageWordDamage = totalWordDamage / wordGame.typedWords.size

            return GameData(
                level = wordGame.level,
                language = wordGame.language,
                specialLetters = wordGame.specialLetters,
                lootedLetters = lootedLetters,
                typedWords = wordGame.typedWords.toList(),
                playTime = playTime,
                timeStamp = LocalDateTime.now().toString(),
                healthRemaining = wordGame.gameField.path.base.health.value,
                enemiesRemaining = wordGame.gameField.enemiesOnField.size + wordGame.gameField.enemiesIncoming.size,
                lettersPerMinute = lettersPerMinute,
                totalWordDamage = totalWordDamage,
                averageWordDamage = averageWordDamage
            )


        }
    }

}