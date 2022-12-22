package persistence

import constants.Language
import constants.Level
import model.Letter
import model.Word
import persistence.repos.GameDataRepo

data class GameData(
    val level: Level,
    val specialLetters: List<Letter>,
    val lootedLetters: List<Letter>,
    val typedWords: List<Word>,
    val playTime: Long,
    val timeStamp: String,
    val healthRemaining: Float,
    val enemiesRemaining: Int,
    val lettersPerMinute: Float,
    val totalWordDamage: Float,
    val averageWordDamage: Float
) {
    fun persist(playerId: Int, language: Language) {
        GameDataRepo.persist(this, playerId, language)
    }
}