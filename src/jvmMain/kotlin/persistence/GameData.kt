package persistence

import constants.Language
import constants.Level
import model.Word

data class GameData(
    val level: Level,
    val language: Language,
    val typedWords: List<Word>,
    val playTime: Long,
    val healthRemaining: Float,
    val enemiesRemaining: Int,
    val lettersPerMinute: Float,
    val totalWordDamage: Float,
    val averageWordDamage: Float
)