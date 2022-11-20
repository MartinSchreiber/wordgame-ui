package util

import constants.Language
import constants.LetterType
import constants.LettersEng
import constants.LettersGer
import model.Letter
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
                    .lineSequence())
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
                    specialLetters.add(
                        Letter(
                            letter = it.second.random(),
                            value = it.first,
                            type = LetterType.STRONGER,
                            specialValue = 5.0
                        )
                    )
                }
            }

            return specialLetters
        }

        fun getKeyCode(nativeKeyCode: Int): Long {
            // First 32 bits are for keycode.
            val keyCode = nativeKeyCode.toLong().shl(32)

            // Next 3 bits are for location.
            val location = (java.awt.event.KeyEvent.KEY_LOCATION_STANDARD.toLong() and 0x7).shl(29)
            return keyCode or location
        }

//        fun getKeyCodeToCharMap(): Map<Long, Char> {
//            //return (97..122).associate { getKeyCode(it) to Char(it) }
//            return (65..90).associate { getKeyCode(it) to Char(it) }
//        }
    }
}