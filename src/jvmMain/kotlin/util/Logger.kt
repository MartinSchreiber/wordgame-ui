package util

import model.Letter
import model.Word
import model.WordGame
import model.gameField.Enemy

class Logger {
    fun logWordsImportedIn(numberOfWords: Int, time: Long) {
        println("$numberOfWords words imported in $time ms")
    }

    fun logWordTyped(word: Word) {
        val values = word.letters.map { it.totalValue }
        println("$word is a ${word.getTotalValue()} point word ")
        println("${values.joinToString("+")} = ${word.getTotalValue()}")
    }

    fun logWordFailure(input: String) {
        println("$input is not a word")
    }

    fun logPartialMove(newX: Float? = null, newY: Float? = null, remainingDistance: Float) {
        newY?.let { print("moveY: $it \t ") }
        newX?.let { print("moveX: $it \t ") }
        print("remainingDistance: $remainingDistance")
        println()
    }

    fun logEnemyMove(enemy: Enemy) {
        println("enemy $enemy moved to (${enemy.position.value.x}|${enemy.position.value.y})")
        if (enemy.reachedEnd()) {
            println("enemy $enemy reached the end!")
        }
    }

    fun logLetterFired(letter: Letter) {
        println("#Letter $letter (${letter.totalValue}) was fired!")
    }

    fun logGameOver(wordGame: WordGame) {
        println("GAME OVER")
        println("Typed Words: ")
        wordGame.typedWords.forEach {
            println("- $it (${it.getTotalValue()})")
        }
    }

    companion object {
        val LOGGER = Logger()
    }
}