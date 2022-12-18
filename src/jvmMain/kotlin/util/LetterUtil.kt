package util

import constants.Language
import constants.LetterType
import constants.lettervalues.LetterValue
import constants.lettervalues.LetterValueEnglish
import constants.lettervalues.LetterValueGerman
import model.Letter

class LetterUtil {
    companion object {
        fun getSpecialLetterMap(): MutableMap<Language, List<Letter>> {
            return Language.values().associateWith { getSpecialLetters(it) }.toMutableMap()
        }

        private fun getSpecialLetters(language: Language): List<Letter> {
            val specialLetters = mutableListOf<Letter>()

            val letterValueGroups = getLetterValueGroups(language)

            letterValueGroups.forEachIndexed { ind, group ->
                val pickCount = 8 / group.first
                for (i in 1..pickCount) {
                    specialLetters.add(
                        Letter(
                            letter = group.second.random(),
                            value = group.first,
                            level = ind + 1,
                            type = LetterType.STRONGER,
                            specialValue = 5f
                        )
                    )
                }
            }

            return specialLetters
        }

        fun getLetterValues(language: Language): Map<Char, Int> {
            return letterValuesOf(language)
                .associate { it.letter to it.value }
        }

        fun getLetterValueGroups(language: Language): List<Pair<Int, List<Char>>> {
            return letterValuesOf(language)
                .groupBy { it.value }
                .map { it.key to it.value.map { c -> c.letter } }
                .sortedBy { it.first }
        }

        private fun letterValuesOf(language: Language): List<LetterValue> {
            return when (language) {
                Language.GERMAN -> LetterValueGerman.values().toList()
                Language.ENGLISH -> LetterValueEnglish.values().toList()
            }
        }
    }
}