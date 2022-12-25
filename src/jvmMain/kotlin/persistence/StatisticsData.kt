package persistence

import constants.Language
import constants.Level
import model.Word
import persistence.repos.StatisticsDataRepo

data class StatisticsData(
    val wordsByValue: List<Word>,
    val wordsByNumber: List<Pair<String, Int>>,
    val totalGamesPlayed: Int,
    val totalWordDamage: Float,
    val averageWordDamage: Float,
    val lettersPerMinute: Float,
    val levelData: List<LevelData>
) {
    fun persist(playerId: Int, language: Language) = StatisticsDataRepo.persist(entity = this, playerId, language)
    data class LevelData(val level: Level, val min: Long, val max: Long, val avg: Float, val gameNumber: Int)
}