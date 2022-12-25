package view.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.Word

@Composable
fun WordList(
    words: List<Word>,
    modifier: Modifier = Modifier.height(400f.dp).fillMaxWidth(),
    content: (Int, Word) -> String
) {
    ScrollableBox(
        modifier = modifier
    ) {
        words.forEachIndexed { index, word ->
            Text(text = content(index, word))
        }
    }
}