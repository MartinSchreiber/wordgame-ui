package persistence

import constants.Language
import model.Letter

class PlayerData(
    val id: Int,
    val name: String,
    var language: Language,
    specialLetters: MutableMap<Language, List<Letter>>
) {
    val laboratory = specialLetters.mapValues { Laboratory(activeLetters = it.value) }.toMutableMap()

    data class Laboratory(
        var activeLetters: List<Letter>,
        var inactiveLetters: List<Letter> = listOf(),
        var combinationChamber: List<Letter> = listOf(),
        var resultChamber: List<Letter> = listOf()
    )
}