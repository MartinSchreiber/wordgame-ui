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
    companion object {
        private val LOGGER = Logger()

        private val PATH = Path()
        private val DEFAULT_INCOMING_ENEMIES = listOf(Enemy(path = PATH))//, Enemy(path = PATH, delay = 60000))
        //TODO: add default-values for path-creation

        private const val TOTAL_LETTER_CHAMBERS = 10
        private const val OPEN_LETTER_CHAMBERS = 1
    }

    private val validWords = Util.importWords(language)
    private val letterValues = Util.getLetterValues(language)
    private val specialLetters = Util.getSpecialLetters(language)
    private val lettersBorrowed = mutableListOf<Pair<Letter, Int>>()

    val allowedLetters = letterValues.keys
    val enemiesOnField: SnapshotStateList<Enemy> = mutableStateListOf()
    val enemyPath: Path = Path()
    val wordQueue: SnapshotStateList<Word> = mutableStateListOf()
    val wordsTyped: SnapshotStateList<Word> = mutableStateListOf()
    val textInput = mutableStateOf("")
    val wordInput = mutableStateOf(Word())
    val letterChambers = LetterChambers(TOTAL_LETTER_CHAMBERS, OPEN_LETTER_CHAMBERS, specialLetters)

    var isOver = { enemiesIncoming.isEmpty() && enemiesOnField.isEmpty() }
    fun updateWord(text: String) {
        textInput.value = text

        if (text.length < wordInput.value.size()) {
            val removedLetter = wordInput.value.removeLastLetter()
            val returnedLetters: MutableList<Pair<Letter, Int>> = mutableListOf()
            lettersBorrowed.forEach {
                if (it.first == removedLetter) {
                    letterChambers.returnLetters(listOf(it))
                    returnedLetters.add(it)
                }
            }
            lettersBorrowed.removeAll(returnedLetters)
        } else if (text.length > wordInput.value.size()) {
            val addedChar = text.last()

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
        textInput.value = textInput.value
        return if (textInput.value.isNotBlank() && validWords.contains(textInput.value)) {

            wordQueue.add(wordInput.value)
            wordsTyped.add(wordInput.value.copy())
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
}