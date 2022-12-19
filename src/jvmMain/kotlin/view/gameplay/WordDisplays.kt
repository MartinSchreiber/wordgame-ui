package view.gameplay

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import model.Word
import view.navigation.AppState

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
    Text(text = AppState.translate("word_queue_label"))
    queue.forEach {
        Word(it)
    }
}

@Composable
fun Word(word: Word) {
    Text(text = "-$word-")
}