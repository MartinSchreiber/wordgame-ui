package persistence

import constants.Language
import model.Letter

data class PlayerData(
    val id: Int,
    val name: String,
    var language: Language,
    var specialLetters: Map<Language, List<Letter>>
) {
    val laboratory = specialLetters.mapValues { Laboratory(it.value) }

    data class Laboratory(
        var activeLetters: List<Letter>,
        var inactiveLetters: List<Letter> = listOf(),
        var combinationChamber: List<Letter> = listOf(),
        var resultChamber: List<Letter> = listOf()
    )
}