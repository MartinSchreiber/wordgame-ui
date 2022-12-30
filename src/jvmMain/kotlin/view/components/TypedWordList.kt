package view.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import model.Word
import view.gameplay.WordDisplay

@Composable
fun WordList(
    words: List<Word>,
    modifier: Modifier = Modifier.height(500f.dp).fillMaxWidth(),
    content: (Int, Word) -> String
) {
    ScrollableBox(
        modifier = modifier
    ) {
        words.forEachIndexed { index, word ->
            Row {
                Text(text = content(index, word), color = Color.White)
                WordDisplay(word)
            }
        }
    }
}