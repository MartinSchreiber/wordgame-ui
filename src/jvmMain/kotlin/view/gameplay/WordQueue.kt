package view.gameplay

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import model.Word
import view.navigation.AppState

@Composable
fun WordQueue(queue: SnapshotStateList<Word>) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = AppState.translate("word_queue_label") + queue.joinToString(separator = "") { "--$it--" },
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )
}