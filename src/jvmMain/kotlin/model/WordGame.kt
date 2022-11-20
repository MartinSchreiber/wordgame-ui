package model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import constants.Language
import util.Logger
import util.Util

class WordGame(
    language: Language = Language.ENGLISH,
    val enemiesIncoming: MutableList<Enemy> = DEFAULT_INCOMING_ENEMIES.toMutableList()
) {

    private val validWords = Util.importWords(language)
    private val letterValues = Util.getLetterValues(language)
    private val specialLetters = Util.getSpecialLetters(language)

    val enemiesOnField: SnapshotStateList<Enemy> = mutableStateListOf()
    val mutableStateQueue = MutableStateQueue<Word>()
    val wordsTyped: SnapshotStateList<Word> = mutableStateListOf()
    val wordInput = mutableStateOf("")
    val letterChambers = LetterChambers(TOTAL_LETTER_CHAMBERS, OPEN_LETTER_CHAMBERS, specialLetters)

    fun addWord(): Boolean {
        wordInput.value = wordInput.value.uppercase()
        return if (wordInput.value.isNotBlank() && validWords.contains(wordInput.value)) {
            val word = buildWord()

            mutableStateQueue.add(word)
            wordsTyped.add(word)

            clearInput()

            LOGGER.log(word)
            true
        } else {
            LOGGER.logWordFailure(wordInput.value)
            false
        }
    }

    private fun buildWord(): Word {
        val openSpecials = letterChambers.getOpenLetters()
        val letters = wordInput.value.map { char ->
            openSpecials
                .firstOrNull { it.letter == char }
                .let { specialLetter ->
                    if (specialLetter != null) {
                        openSpecials.remove(specialLetter)
                        specialLetter
                    } else {
                        Letter(letter = char, value = letterValues[char]!!)
                    }
                }
        }
        letterChambers.remove(letters)

        return Word(letters)
    }

    //TODO implement dynamic letter-by-letter display (second field?)
    fun type(char: Char) {
        wordInput.value = wordInput.value + char
    }

    fun clearInput() {
        wordInput.value = ""
    }

    fun isOver(): Boolean = enemiesIncoming.isEmpty() && enemiesOnField.isEmpty()

    companion object {
        val LOGGER = Logger()
        val DEFAULT_INCOMING_ENEMIES = listOf<Enemy>()
        const val TOTAL_LETTER_CHAMBERS = 10
        const val OPEN_LETTER_CHAMBERS = 1
    }
}