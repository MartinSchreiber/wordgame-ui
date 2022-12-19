package constants

enum class Language(val wordFile: String, val labelFile: String) {
    GERMAN(
        wordFile = "src/jvmMain/resources/words/words_ger.txt",
        labelFile = "src/jvmMain/resources/labels/labels_ger.json"
    ),
    ENGLISH(
        wordFile = "src/jvmMain/resources/words/words_eng.txt",
        labelFile = "src/jvmMain/resources/labels/labels_eng.json"
    )
}