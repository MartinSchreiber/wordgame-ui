package model

import androidx.compose.runtime.mutableStateListOf
import java.util.*

class WordQueue : Queue<Word>{
    private val words = mutableStateListOf<Word>()

    fun getMutableStateList() = words

    override fun add(element: Word?): Boolean {
        return if (element != null) {
            words.add(element)
        } else {
            false
        }
    }

    override fun addAll(elements: Collection<Word>): Boolean {
        return words.addAll(elements)
    }

    override fun clear() {
        words.clear()
    }

    override fun iterator(): MutableIterator<Word> {
        return words.iterator()
    }

    override fun remove(): Word {
        return words.removeFirst()
    }

    override fun retainAll(elements: Collection<Word>): Boolean {
        return words.retainAll(elements)
    }

    override fun removeAll(elements: Collection<Word>): Boolean {
        return words.removeAll(elements)
    }

    override fun remove(element: Word?): Boolean {
        return words.remove(element)
    }

    override fun isEmpty(): Boolean {
        return words.isEmpty()
    }

    override fun poll(): Word? {
        return if (words.isNotEmpty()) {
            words.first()
        } else {
            null
        }
    }

    override fun element(): Word {
        return words.first()
    }

    override fun peek(): Word? {
        return if (words.isNotEmpty()) {
            words.first()
        } else {
            null
        }
    }

    override fun offer(e: Word?): Boolean {
        return if (e != null) {
            words.add(e)
        } else {
            false
        }
    }

    override fun containsAll(elements: Collection<Word>): Boolean {
        return words.containsAll(elements)
    }

    override fun contains(element: Word?): Boolean {
        return words.contains(element)
    }

    override val size: Int
        get() = words.size

}