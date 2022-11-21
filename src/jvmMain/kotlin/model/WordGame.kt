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
    private val lettersBorrowed = mutableListOf<Pair<Letter, Int>>()

    val enemiesOnField: SnapshotStateList<Enemy> = mutableStateListOf()
    val wordQueue = MutableStateQueue<Word>()
    val wordsTyped: SnapshotStateList<Word> = mutableStateListOf()
    val textInput = mutableStateOf("")
    val wordInput = mutableStateOf(Word())
    val letterChambers = LetterChambers(TOTAL_LETTER_CHAMBERS, OPEN_LETTER_CHAMBERS, specialLetters)

    fun updateWord(text: String) {
        textInput.value = text

        if (text.length < wordInput.value.size()) {
            val removedLetter = wordInput.value.removeLetter()
            val returnedLetters: MutableList<Pair<Letter, Int>> = mutableListOf()
            lettersBorrowed.forEach {
                if (it.first == removedLetter) {
                    letterChambers.returnLetters(listOf(it))
                    returnedLetters.add(it)
                }
            }
            lettersBorrowed.removeAll(returnedLetters)
        } else if (text.length > wordInput.value.size()) {
            val addedChar = text.last().uppercaseChar()

            val specialLetter = letterChambers.borrowLetter(addedChar)
            specialLetter?.let { lettersBorrowed.add(it) }

            val addedLetter: Letter = specialLetter?.first ?: Letter(
                letter = addedChar,
                value = letterValues[addedChar]!!
            )

            wordInput.value.addLetter(addedLetter)
        }
    }

    fun addWord(): Boolean {
        textInput.value = textInput.value.uppercase()
        return if (textInput.value.isNotBlank() && validWords.contains(textInput.value)) {

            wordQueue.add(wordInput.value)
            wordsTyped.add(wordInput.value)
            letterChambers.remove(lettersBorrowed.map { it.second })

            LOGGER.log(wordInput.value)
            clearInput(false)

            true
        } else {
            LOGGER.logWordFailure(textInput.value)
            false
        }
    }

    fun clearInput(returnBorrowed: Boolean = true) {
        textInput.value = ""
        wordInput.value = Word()

        if (returnBorrowed) {
            letterChambers.returnLetters(lettersBorrowed)
        }
        lettersBorrowed.clear()
    }

    fun isOver(): Boolean = enemiesIncoming.isEmpty() && enemiesOnField.isEmpty()

    companion object {
        val LOGGER = Logger()
        val DEFAULT_INCOMING_ENEMIES = listOf<Enemy>()

        const val TOTAL_LETTER_CHAMBERS = 10
        const val OPEN_LETTER_CHAMBERS = 1
    }
}