package constants

enum class LetterType {
    BASIC, STRONGER, MULTIPLY;

    fun getNextType(): LetterType? {
        return when (this) {
            STRONGER -> MULTIPLY
            else -> null
        }
    }
}