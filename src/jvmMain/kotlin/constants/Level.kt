package constants

enum class Level() {
    ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN;

    fun hasNext(): Boolean {
        return ordinal < values().size - 1
    }

    fun getNext(): Level {
        return values()[ordinal + 1]
    }
}