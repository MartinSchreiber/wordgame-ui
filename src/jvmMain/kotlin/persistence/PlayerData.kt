package persistence

import constants.Language
import model.Letter
import persistence.repos.PlayerDataRepo

data class PlayerData(
    val id: Int,
    val name: String,
    var language: Language,
    val laboratory: MutableMap<Language, Laboratory>
) {

    fun persist() = PlayerDataRepo.persist(entity = this)

    data class Laboratory(
        var activeLetters: List<Letter>,
        var inactiveLetters: MutableList<Letter> = mutableListOf(),
        var combinationChamber: List<Letter> = listOf(),
        var resultChamber: List<Letter> = listOf()
    )
}