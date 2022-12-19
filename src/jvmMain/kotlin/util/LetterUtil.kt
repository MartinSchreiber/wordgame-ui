package util

import constants.Language
import constants.LetterType
import constants.Level
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
            return getRandomLetters(pickValue = 8, language = language)
        }

        //TODO: refine logic
        fun getLootedLetters(level: Level, language: Language): List<Letter> {
            val randomLetters = getRandomLetters(pickValue = level.ordinal + 1, language = language)
            val letters = mutableListOf<Letter>()
            for (i in 0..level.ordinal) {
                letters.add(randomLetters.random())
            }
            return letters
        }

        //TODO: refine logic
        private fun getRandomLetters(pickValue: Int? = null, language: Language): List<Letter> {
            val letters = mutableListOf<Letter>()

            val letterValueGroups = getLetterValueGroups(language)

            letterValueGroups.forEachIndexed { ind, group ->
                val pickCount = (pickValue ?: letterValueGroups.maxOf { it.first }) / group.first
                for (i in 1..pickCount) {
                    letters.add(
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

            return letters
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

        fun combine(letters: List<Letter>, language: Language): List<Letter> {
            val lettersMutable = letters.toMutableList()
            val sameTypeAndLevel =
                letters.map { it.type }.distinct().size == 1 && letters.map { it.level }.distinct().size == 1
            val combinedLetters = mutableListOf<Letter>()

            if (sameTypeAndLevel && letters.size % 2 == 0) {
                while (lettersMutable.isNotEmpty()) {
                    lettersMutable.removeFirst()
                    lettersMutable.removeFirst()
                    combine(letters.first().level, letters.first().type, language)?.let { combinedLetters.add(it) }
                }
            }

            return combinedLetters
        }

        private fun combine(letterLevel: Int, letterType: LetterType, language: Language): Letter? {
            val letterValueGroups = getLetterValueGroups(language)

            return if (letterLevel < letterValueGroups.size) {
                val specialValue = when (letterType) {
                    LetterType.BASIC -> 0f
                    LetterType.STRONGER -> 5f
                    LetterType.MULTIPLY -> 2f
                }
                Letter(
                    letter = letterValueGroups[letterLevel].second.random(),
                    value = letterValueGroups[letterLevel].first,
                    level = letterLevel + 1,
                    type = letterType,
                    specialValue = specialValue
                )
            } else if (letterType.getNextType() != null) {
                val specialValue = when (letterType.getNextType()!!) {
                    LetterType.BASIC -> 0f
                    LetterType.STRONGER -> 5f
                    LetterType.MULTIPLY -> 2f
                }
                Letter(
                    letter = letterValueGroups[0].second.random(),
                    value = letterValueGroups[0].first,
                    level = 1,
                    type = letterType.getNextType()!!,
                    specialValue = specialValue
                )
            } else {
                null
            }
        }
    }
}