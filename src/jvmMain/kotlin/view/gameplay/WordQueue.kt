package view.gameplay

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import model.Word
import view.navigation.AppState

@Composable
fun WordQueue(queue: SnapshotStateList<Word>) {
    Row(modifier = Modifier.fillMaxWidth().height(30f.dp)) {
        Text(text = AppState.translate("word_queue_label"), color = Color.White)

        queue.forEach {
            Spacer(Modifier.size(10f.dp))

            WordDisplay(it)
        }
    }

}