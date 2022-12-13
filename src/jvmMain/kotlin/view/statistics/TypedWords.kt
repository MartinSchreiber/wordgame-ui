package view.statistics

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import model.Word

@Composable
fun TypedWords(words: List<Word>) {
    Column {
        words.sortedByDescending { it.getTotalValue() }.forEach { word ->
            Text(text = "$word <${word.getTotalValue()}>")
        }
    }
}