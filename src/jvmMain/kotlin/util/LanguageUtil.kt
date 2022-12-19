package util

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import constants.Language
import java.io.File

class LanguageUtil(language: Language) {
    private inline fun <reified T> genericType() = object : TypeToken<T>() {}.type

    val file = File(language.labelFile)
    val gson = GsonBuilder().create()
    val labels: Map<String, String> = gson.fromJson(file.readText(), genericType<Map<String, String>>())

    fun translate(label: String): String {
        return labels[label] ?: label
    }
}