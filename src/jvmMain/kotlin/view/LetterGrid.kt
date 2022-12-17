package view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.PointerMatcher
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.onClick
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerButton
import model.Letter

@Composable
fun LetterGrid(
    letters: SnapshotStateList<Letter>,
    title: String,
    subTitle: String,
    lettersPerRow: Int = 10,
    onClick: (Letter, Boolean) -> Unit
) {
    Text(text = "----$title--------")
    Text(text = "------------------")
    Text(text = "----$subTitle--------")
    LazyVerticalGrid(columns = GridCells.Fixed(lettersPerRow)) {
        items(letters.size) {
            ClickableLetter(letters[it]) { letter: Letter, rightMouseBtn: Boolean ->
                onClick(letter, rightMouseBtn)
                letters.removeAt(it)
            }
        }
    }
    Text(text = "------------------")
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ClickableLetter(letter: Letter, onClick: (Letter, Boolean) -> Unit) {
    Box(modifier = Modifier
        .onClick(matcher = PointerMatcher.mouse(PointerButton.Secondary), onClick = { onClick(letter, true) })
        .onClick { onClick(letter, false) }
    ) {
        Text(text = letter.toString(), modifier = Modifier.align(Alignment.Center))
    }
}