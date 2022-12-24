package view.gameplay

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import model.Word
import view.components.ScrollableBox

@Composable
fun TypedWordList(words: List<Word>, content: (Int, Word) -> String) {
    ScrollableBox {
        words.forEachIndexed { index, word ->
            Text(text = content(index, word))
        }
    }
}