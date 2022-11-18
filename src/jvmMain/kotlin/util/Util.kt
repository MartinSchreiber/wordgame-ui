package util

import model.Letter
import constants.Language
import constants.LettersEng
import constants.LettersGer
import java.io.File
import kotlin.system.measureTimeMillis

class Util {
    companion object {
        fun importWords(language: Language = Language.ENGLISH): Set<String> {
            val words = mutableSetOf<String>()

            val time = measureTimeMillis {
                val inputStream = File(language.file).inputStream()
                words.addAll(inputStream
                    .bufferedReader()
                    .lineSequence()
                    .map { it.lowercase() }) //TODO convert files and everything to uppercase
            }

            println("${words.size} words imported in $time ms")
            return words
        }

        fun getLetterValues(language: Language = Language.ENGLISH): Map<Char, Int> {
            return when (language) {
                Language.ENGLISH -> LettersEng.values().associate { it.letter to it.value }
                Language.GERMAN -> LettersGer.values().associate { it.letter to it.value }
            }
        }

        fun getSpecialLetters(language: Language = Language.ENGLISH): List<Letter> {
            val specialLetters = mutableListOf<Letter>()

            val letters = when (language) {
                Language.ENGLISH -> LettersEng.values()
                    .groupBy { it.value }
                    .map { it.key to it.value.map { c -> c.letter } }

                Language.GERMAN -> LettersGer.values()
                    .groupBy { it.value }
                    .map { it.key to it.value.map { c -> c.letter } }
            }

            letters.forEach {
                val pickCount = 8 / it.first
                for (i in 1..pickCount) {
                    specialLetters.add(Letter(it.second.random(), it.first))
                }
            }

            return specialLetters
        }
    }
}