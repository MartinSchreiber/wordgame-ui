package model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import constants.Language
import model.gameField.GameField
import util.ImportUtil
import util.LetterUtil
import util.Logger

class WordGame(language: Language = Language.ENGLISH) {
    companion object {
        private const val TOTAL_LETTER_CHAMBERS = 10
        private const val OPEN_LETTER_CHAMBERS = 1
    }

    private val validWords = ImportUtil.importWords(language)
    private val letterValues = LetterUtil.getLetterValues(language)
    private val specialLetters = LetterUtil.getSpecialLetters(language)
    private val borrowedLetters = mutableListOf<Pair<Letter, Int>>()

    val allowedLetters = letterValues.keys
    val typedWords: SnapshotStateList<Word> = mutableStateListOf()

    val textInput = mutableStateOf("")
    val wordInput = mutableStateOf(Word())
    val wordQueue: SnapshotStateList<Word> = mutableStateListOf()
    val letterChambers = LetterChambers(TOTAL_LETTER_CHAMBERS, OPEN_LETTER_CHAMBERS, specialLetters)

    val gameField = GameField()

    var isOver = { gameField.isOver() }
    fun updateWord(text: String) {
        textInput.value = text

        if (text.length < wordInput.value.size()) {
            val removedLetter = wordInput.value.removeLastLetter()
            val returnedLetters: MutableList<Pair<Letter, Int>> = mutableListOf()
            borrowedLetters.forEach {
                if (it.first == removedLetter) {
                    letterChambers.returnLetters(listOf(it))
                    returnedLetters.add(it)
                }
            }
            borrowedLetters.removeAll(returnedLetters)
        } else if (text.length > wordInput.value.size()) {
            val addedChar = text.last()

            val specialLetter = letterChambers.borrowLetter(addedChar)
            specialLetter?.let { borrowedLetters.add(it) }

            val addedLetter: Letter = specialLetter?.first ?: Letter(
                letter = addedChar,
                value = letterValues[addedChar]!!
            )

            wordInput.value.addLetter(addedLetter)
        }
    }

    fun addWord(): Boolean {
        textInput.value = textInput.value
        return if (textInput.value.isNotBlank() && validWords.contains(textInput.value)) {


            wordQueue.add(wordInput.value)
            typedWords.add(wordInput.value.copy())
            letterChambers.remove(borrowedLetters.map { it.second })

            Logger.LOGGER.logWordTyped(wordInput.value)
            clearInput(false)

            true
        } else {
            Logger.LOGGER.logWordFailure(textInput.value)
            false
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
}