package persistence

import constants.Language
import model.Letter

data class PlayerData(
    val id: Int,
    val name: String,
    var language: Language,
    var specialLetters: Map<Language, List<Letter>>
)