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
            val totalWordDamage = wordGame.typedWords.sumOf { it.totalValue().toDouble() }.toFloat()
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
            val laboratory = mapOf(Pair(language, LetterUtil.getSpecialLetters(language))).mapValues {
                PlayerData.Laboratory(
                    activeLetters = it.value
                )
            }.toMutableMap()

            return PlayerData(id = PlayerDataRepo.getList().maxOf { it.id } + 1,
                name = name,
                language = language,
                laboratory = laboratory
            )
        }

        //TODO limit size of word-lists
        fun toStatisticsData(gameDataList: List<GameData>): StatisticsData {
            val allWords = gameDataList.flatMap { it.typedWords }

            val wordsByValue = allWords
                .distinctBy { it.toString() }
                .sortedByDescending { it.totalValue() }

            val wordsByNumber = allWords
                .groupBy { it.toPlainString() }
                .map { Pair(it.key, it.value.size) }
                .sortedByDescending { it.second }

            val totalGamesPlayed = gameDataList.size

            val totalWordDamage = gameDataList.sumOf { it.totalWordDamage.toDouble() }.toFloat()

            val averageWordDamage = allWords.sumOf { it.totalValue().toDouble() }.toFloat() / allWords.size

            val lettersPerMinute = allWords.sumOf { it.size() } / (gameDataList.sumOf { it.playTime } / 60000f)

            val levelData = gameDataList
                .groupBy { it.level }
                .map { entry ->
                    StatisticsData.LevelData(
                        level = entry.key,
                        min = entry.value.minOf { it.playTime },
                        max = entry.value.maxOf { it.playTime },
                        avg = entry.value.sumOf { it.playTime } / entry.value.size.toFloat(),
                        gameNumber = entry.value.size)
                }
                .sortedBy { it.level }

            return StatisticsData(
                wordsByValue = wordsByValue,
                wordsByNumber = wordsByNumber,
                totalGamesPlayed = totalGamesPlayed,
                totalWordDamage = totalWordDamage,
                averageWordDamage = averageWordDamage,
                lettersPerMinute = lettersPerMinute,
                levelData = levelData
            )

        }
    }
}