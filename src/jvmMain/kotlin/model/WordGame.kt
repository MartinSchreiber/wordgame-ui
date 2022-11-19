package model

import util.Util
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import constants.Language
import util.Logger

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
        return if (wordInput.value.isNotBlank() && validWords.contains(wordInput.value)) {
            if (wordInput.value.length >= letterChambers.opened.value) {
                letterChambers.open()
            }
            val word = buildWord()

            mutableStateQueue.add(word)
            wordsTyped.add(word)

            clearInput()

            //TODO outsource to Logger
            val values = word.letters.map { it.value }
            println("${wordInput.value} is a ${word.getTotalValue()} point word ")
            println("${values.joinToString("+")} +  = ${word.getTotalValue()}")

            true
        } else {
            println("${wordInput.value} is not a word")
            false
        }
    }

    //TODO use letters from open chambers if possible
    private fun buildWord(): Word {
        val openSpecials = letterChambers.getOpenLetters()
        val letters = wordInput.value.map { char ->
            openSpecials
                .firstOrNull { it.letter == char }
                .let { specialLetter ->
                    if (specialLetter != null) {
                        openSpecials.remove(specialLetter)
                        letterChambers.removeLetters(listOf(specialLetter))
                        specialLetter
                    } else {
                        Letter(letter = char, value = letterValues[char]!!)
                    }
                }
        }

        return Word(letters)
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

    //TODO: create model.view.Word from Input (with potential special letters from list), Fill word list, empty wordlist (but log typed words)
}