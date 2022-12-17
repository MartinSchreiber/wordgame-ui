package util

import constants.Language
import constants.LetterType
import constants.LettersEng
import constants.LettersGer
import model.Letter

class LetterUtil {
    companion object {

        fun getLetterValues(language: Language = Language.ENGLISH): Map<Char, Int> {
            return when (language) {
                Language.ENGLISH -> LettersEng.values().associate { it.letter to it.value }
                Language.GERMAN -> LettersGer.values().associate { it.letter to it.value }
            }
        }

        fun getLetterValueGroups(language: Language = Language.ENGLISH): List<Pair<Int, List<Char>>> {
            return when (language) {
                Language.ENGLISH -> LettersEng.values()
                    .groupBy { it.value }
                    .map { it.key to it.value.map { c -> c.letter } }

                Language.GERMAN -> LettersGer.values()
                    .groupBy { it.value }
                    .map { it.key to it.value.map { c -> c.letter } }
            }
        }

        fun getSpecialLetterMap(): MutableMap<Language, List<Letter>> {
            return Language.values().associateWith { getSpecialLetters(it) }.toMutableMap()
        }

        private fun getSpecialLetters(language: Language = Language.ENGLISH): List<Letter> {
            val specialLetters = mutableListOf<Letter>()

            val letterValueGroups = getLetterValueGroups(language)

            letterValueGroups.forEach {
                val pickCount = 8 / it.first
                for (i in 1..pickCount) {
                    specialLetters.add(
                        Letter(
                            letter = it.second.random(),
                            value = it.first,
                            type = LetterType.STRONGER,
                            specialValue = 5f
                        )
                    )
                }
            }

            return specialLetters
        }

    }
}