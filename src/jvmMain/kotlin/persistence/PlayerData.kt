package persistence

import constants.Language
import model.Letter

data class PlayerData(
    val id: Int,
    val name: String,
    val language: Language,
    val specialLetters: Map<Language, List<Letter>>
)