package persistence

import constants.Language
import model.Letter
import persistence.repos.PlayerDataRepo

class PlayerData(
    val id: Int,
    val name: String,
    var language: Language,
    specialLetters: MutableMap<Language, List<Letter>>
) {
    val laboratory = specialLetters.mapValues { Laboratory(activeLetters = it.value) }.toMutableMap()

    fun persist() {
        PlayerDataRepo.persist(entity = this)
    }

    data class Laboratory(
        var activeLetters: List<Letter>,
        var inactiveLetters: MutableList<Letter> = mutableListOf(),
        var combinationChamber: List<Letter> = listOf(),
        var resultChamber: List<Letter> = listOf()
    )
}