package constants

enum class LetterType(val image: String) {
    BASIC("svg/letter_iron.svg"),
    STRONGER("svg/letter_lead.svg"),
    MULTIPLY("svg/letter_lithium.svg");

    fun getNextType(): LetterType? {
        return when (this) {
            STRONGER -> MULTIPLY
            else -> null
        }
    }
}