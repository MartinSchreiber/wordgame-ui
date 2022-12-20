package util

import constants.Language
import java.io.File

class ImportUtil {
    companion object {
        fun importWords(language: Language = Language.ENGLISH): Set<String> {
            return File(language.wordFile)
                .inputStream()
                .bufferedReader()
                .lineSequence()
                .toSet()
        }
    }
}