package persistence

import constants.Language
import model.Letter
import model.WordGame
import persistence.repos.PlayerDataRepo
import util.LetterUtil
import java.time.LocalDateTime

class Converter {
    companion object {
        fun toGameData(wordGame: WordGame, lootedLetters: List<Letter>): GameData {
            val playTime = (wordGame.endTime!! - wordGame.startTime)
            val lettersPerMinute = wordGame.typedWords.sumOf { it.size() } / (playTime / 60000f)
            val totalWordDamage = wordGame.typedWords.sumOf { it.getTotalValue().toDouble() }.toFloat()
            val averageWordDamage = totalWordDamage / wordGame.typedWords.size

            return GameData(
                level = wordGame.level,
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

        fun toPlayerData(name: String, language: Language = Language.ENGLISH): PlayerData {
            return PlayerData(
                id = PlayerDataRepo.getList().maxOf { it.id } + 1,
                name = name,
                language = language,
                specialLetters = mutableMapOf(Pair(language, LetterUtil.getSpecialLetters(language)))
            )
        }
    }
}