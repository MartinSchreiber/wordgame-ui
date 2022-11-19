package model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import java.util.*

//TODO: implement StateObject interface
class MutableStateQueue<T> : Queue<T> {
    private val elementList = mutableStateListOf<T>()

    fun toMutableStateList() : SnapshotStateList<T> {
        return elementList
    }

    override fun add(element: T?): Boolean {
        return if (element != null) {
            elementList.add(element)
        } else {
            false
        }
    }

    override fun addAll(elements: Collection<T>): Boolean {
        return elementList.addAll(elements)
    }

    override fun clear() {
        elementList.clear()
    }

    override fun iterator(): MutableIterator<T> {
        return elementList.iterator()
    }

    override fun remove(): T {
        return elementList.removeFirst()
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        return elementList.retainAll(elements)
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        return elementList.removeAll(elements)
    }

    override fun remove(element: T?): Boolean {
        return elementList.remove(element)
    }

    override fun isEmpty(): Boolean {
        return elementList.isEmpty()
    }

    override fun poll(): T? {
        return if (elementList.isNotEmpty()) {
            elementList.first()
        } else {
            null
        }
    }

    override fun element(): T {
        return elementList.first()
    }

    override fun peek(): T? {
        return if (elementList.isNotEmpty()) {
            elementList.first()
        } else {
            null
        }
    }

    override fun offer(e: T?): Boolean {
        return if (e != null) {
            elementList.add(e)
        } else {
            false
        }
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        return elementList.containsAll(elements)
    }

    override fun contains(element: T?): Boolean {
        return elementList.contains(element)
    }

    override val size: Int
        get() = elementList.size
}