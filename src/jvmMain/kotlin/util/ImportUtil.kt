package util

import constants.Language
import java.io.File
import kotlin.system.measureTimeMillis

class ImportUtil {
    companion object {
        fun importWords(language: Language = Language.ENGLISH): Set<String> {
            val words = mutableSetOf<String>()

            val time = measureTimeMillis {
                words.addAll(
                    File(language.wordFile)
                        .inputStream()
                        .bufferedReader()
                        .lineSequence()
                )
            }

            Logger.LOGGER.logWordsImportedIn(words.size, time)

            return words
        }
    }
}