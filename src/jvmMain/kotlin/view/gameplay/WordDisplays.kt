package view.gameplay

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import model.Word

@Composable
fun WordsTyped(words: SnapshotStateList<Word>) {
    Column {
        words.forEachIndexed { index, word ->
            Text(text = "$index - $word")
        }
    }
}

@Composable
fun WordQueue(queue: SnapshotStateList<Word>) {
    Text(text = "Queue: ")
    queue.forEach {
        Word(it)
    }
}

@Composable
fun Word(word: Word) {
    Text(text = "-$word-")
}