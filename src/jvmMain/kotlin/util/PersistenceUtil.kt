package util

import com.google.gson.GsonBuilder
import model.WordGame
import persistence.GameData
import persistence.PlayerData
import view.navigation.AppState

class PersistenceUtil {
    //TODO read/write player- and game-data
    companion object {
        fun getPlayerData(): List<PlayerData> {
            return listOf()
        }

        fun persistPlayer(name: String): PlayerData {
            val playerData = createPlayer(name)
            writeJSON(playerData)
            return playerData
        }

        fun persistGame(wordGame: WordGame): GameData {
            val gameData = convertWordGame(wordGame)
            writeJSON(gameData)
            return gameData
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

        private fun writeJSON(obj: Any) {
            println(GsonBuilder().create().toJson(obj))
        }
    }

}