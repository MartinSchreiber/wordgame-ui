package util

import constants.Language
import java.io.File

class LanguageUtil(language: Language) {
    private val labels: Map<String, String> = PersistenceUtil.getLabels(language)

    fun translate(key: String): String {
        return labels[key] ?: key
    }

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