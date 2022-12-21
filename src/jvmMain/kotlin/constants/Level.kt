package constants

enum class Level() {
    L1, L2, L3, L4, L5, L6, L7, L8, L9, L10;

    fun hasNext(): Boolean {
        return ordinal < values().size - 1
    }

    fun getNext(): Level {
        return values()[ordinal + 1]
    }
}