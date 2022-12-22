package util

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import constants.Language
import java.io.File

class LanguageUtil(language: Language) {
    companion object {
        private val GSON = GsonBuilder().create()

        fun importWords(language: Language = Language.ENGLISH): Set<String> {
            return File(language.wordFile)
                .inputStream()
                .bufferedReader()
                .lineSequence()
                .toSet()
        }
    }

    private val labels: Map<String, String> = getLabels(language)


    fun translate(key: String): String {
        return labels[key] ?: key
    }

    private inline fun <reified T> genericType() = object : TypeToken<T>() {}.type

    private fun getLabels(language: Language): Map<String, String> {
        val file = File(language.labelFile)
        if (file.isFile) {
            return GSON.fromJson(file.readText(), genericType<Map<String, String>>())
        }
        return mapOf()
    }

}