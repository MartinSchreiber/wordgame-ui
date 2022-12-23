package util

import constants.Language
import constants.LetterType
import constants.Level
import constants.lettervalues.LetterValue
import constants.lettervalues.LetterValueEnglish
import constants.lettervalues.LetterValueGerman
import model.Letter
import java.util.*

class LetterUtil {
    companion object {
        fun getSpecialLetters(language: Language): List<Letter> = drawLetters(number = 18, language = language)

        fun getLootedLetters(level: Level, language: Language): List<Letter> =
            drawLetters(
                number = (0..3).random(), language = language, maxLevel = (level.ordinal / 10) + 1
            )

        fun getLetterValues(language: Language): Map<Char, Int> =
            getLetterValueObjects(language).associate { it.letter to it.value }

        fun getLetterValueMap(language: Language): SortedMap<Int, List<LetterValue>> =
            getLetterValueObjects(language).groupBy { it.value }.toSortedMap()

        fun combine(letters: List<Letter>, language: Language): List<Letter> {
            val lettersHaveSameTypeAndLevel =
                letters.map { it.type }.distinct().size == 1 && letters.map { it.level }.distinct().size == 1

            val lettersMutable = letters.toMutableList()
            val combinedLetters = mutableListOf<Letter>()

            if (lettersHaveSameTypeAndLevel && letters.size % 2 == 0) {
                while (lettersMutable.isNotEmpty()) {
                    val oldLetters = listOf(lettersMutable.removeFirst(), lettersMutable.removeFirst())

                    val newLetter = combine(letters.first().level, letters.first().type, language)

                    // if letters couldn't be combined return original letters
                    if (newLetter != null) {
                        combinedLetters.add(newLetter)
                    } else {
                        combinedLetters.addAll(oldLetters)
                    }
                }
            }

            return combinedLetters
        }

        private fun combine(letterLevel: Int, letterType: LetterType, language: Language): Letter? {
            val letterValueMap = getLetterValueMap(language)
            val letterLevelValues = letterValueMap.keys.sortedDescending()

            return if (letterLevel < letterValueMap.size) {
                Letter(
                    letter = letterValueMap[letterLevelValues[letterLevel - 1]]!!.random().letter,
                    value = letterLevelValues[letterLevel - 1],
                    level = letterLevel + 1,
                    type = letterType
                )
            } else if (letterType.getNextType() != null) {
                Letter(
                    letter = letterValueMap.values.first().random().letter,
                    value = letterLevelValues[0],
                    level = 1,
                    type = letterType.getNextType()!!
                )
            } else {
                null
            }
        }

        private fun drawLetters(
            number: Int,
            language: Language,
            maxLevel: Int = -1,
            type: LetterType = LetterType.STRONGER
        ): List<Letter> {
            val letters = mutableListOf<Letter>()

            var fullLetterList = getFullLetterList(language, type)

            if (maxLevel > 0) {
                fullLetterList = fullLetterList.filter { it.level <= maxLevel }
            }

            repeat(number) { _ -> letters.add(fullLetterList.random()) }

            getLetterValueObjects(language)

            return letters
        }

        private fun getFullLetterList(language: Language, type: LetterType = LetterType.BASIC): List<Letter> {
            val letterList = mutableListOf<Letter>()

            val letterValueMap = getLetterValueMap(language)
            val letterLevelValues = letterValueMap.keys.sorted()

            letterValueMap.forEach {
                it.value.forEach { value ->
                    repeat(value.count) { _ ->
                        letterList.add(
                            Letter(
                                letter = value.letter,
                                value = it.key,
                                level = letterLevelValues.indexOf(it.key) + 1,
                                type = type
                            )
                        )
                    }
                }
            }

            return letterList
        }

        private fun getLetterValueObjects(language: Language): List<LetterValue> = when (language) {
            Language.GERMAN -> LetterValueGerman.values().toList()
            Language.ENGLISH -> LetterValueEnglish.values().toList()
        }
    }
}