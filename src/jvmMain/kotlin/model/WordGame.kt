package model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import constants.Language
import constants.Level
import model.gameField.GameField
import util.LanguageUtil
import util.LetterUtil

class WordGame(
    val language: Language = Language.ENGLISH, val level: Level = Level.L1, val specialLetters: List<Letter>
) {
    companion object {
        private const val TOTAL_LETTER_CHAMBERS = 10
        private const val OPEN_LETTER_CHAMBERS = 1
    }

    // words and letters for evaluation
    private val validWords = LanguageUtil.importWords(language)
    private val letterValues = LetterUtil.getLetterValues(language)
    private val allowedLetters = letterValues.keys

    // mutable states (partially capsuled in custom classes) for view
    private val borrowedLetters = mutableListOf<Pair<Letter, Int>>()

    val textInput = mutableStateOf("")
    val wordInput = mutableStateOf(Word())

    val typedWords: SnapshotStateList<Word> = mutableStateListOf()
    val wordQueue: SnapshotStateList<Word> = mutableStateListOf()

    val letterChambers = LetterChambers(TOTAL_LETTER_CHAMBERS, OPEN_LETTER_CHAMBERS, specialLetters)
    val gameField = GameField(level = level)

    // statistical fields and end-condition
    val startTime = System.currentTimeMillis()
    var endTime: Long? = null

    var isOver = { gameField.isOver() }

    fun isAllowedLetter(char: Char): Boolean = allowedLetters.contains(char)

    fun updateWord(text: String) {
        textInput.value = text


        if (text.length > wordInput.value.size()) {
            addChar(text.last())
        } else if (text.length < wordInput.value.size()) {
            removeLastLetter()
        }
    }

    fun enqueueWord() {
        textInput.value = textInput.value

        if (textInput.value.isNotBlank()
            && validWords.contains(textInput.value)
            && !alreadyTyped(textInput.value)
        ) {
            wordInput.value.prepareForFiring()

            wordQueue.add(wordInput.value)
            typedWords.add(wordInput.value.copy())
            letterChambers.remove(borrowedLetters.map { it.second })

            clearInput(false)
        }
    }

    fun clearInput(returnBorrowed: Boolean = true) {
        textInput.value = ""
        wordInput.value = Word()

        if (returnBorrowed) {
            letterChambers.returnLetters(borrowedLetters)
        }

        borrowedLetters.clear()
    }

    private fun addChar(char: Char) {
        // borrow special letter if possible
        val specialLetter = letterChambers.borrowLetter(char)
        specialLetter?.let { borrowedLetters.add(it) }

        val addedLetter: Letter = specialLetter?.first ?: Letter(
            letter = char, value = letterValues[char]!!
        )

        wordInput.value.addLetter(addedLetter)
    }

    private fun removeLastLetter() {
        val removedLetter = wordInput.value.removeLastLetter()
        val returnedLetters: MutableList<Pair<Letter, Int>> = mutableListOf()

        // return to special letters if it was borrowed
        borrowedLetters.forEach {
            if (it.first == removedLetter) {
                letterChambers.returnLetters(listOf(it))
                returnedLetters.add(it)
            }
        }

        borrowedLetters.removeAll(returnedLetters)
    }

    private fun alreadyTyped(word: String) = typedWords.map { it.toPlainString() }.contains(word)
}