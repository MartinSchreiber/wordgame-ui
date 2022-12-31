package view.laboratory

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.PointerMatcher
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.onClick
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.PointerButton
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import model.Letter

@OptIn(ExperimentalUnitApi::class)
@Composable
fun LetterGrid(
    letters: SnapshotStateList<Letter>,
    title: String,
    subTitle: String,
    lettersPerRow: Int = 10,
    onClick: (Int, Boolean) -> Unit
) {
    Text(
        text = title,
        color = Color.White,
        fontSize = TextUnit(20f, TextUnitType.Sp),
        textDecoration = TextDecoration.Underline
    )

    Text(text = subTitle, color = Color.White, fontSize = TextUnit(16f, TextUnitType.Sp), fontStyle = FontStyle.Italic)

    LazyVerticalGrid(columns = GridCells.Fixed(lettersPerRow)) {
        items(letters.size) {
            ClickableLetter(letters[it]) { rightMouseBtn: Boolean -> onClick(it, rightMouseBtn) }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ClickableLetter(letter: Letter, onClick: (Boolean) -> Unit) {
    Box(modifier = Modifier
        .onClick(matcher = PointerMatcher.mouse(PointerButton.Secondary), onClick = { onClick(true) })
        .onClick { onClick(false) }
    ) {
        LetterDisplayLaboratory(letter = letter, scale = 1.5f)
    }
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun LetterDisplayLaboratory(
    letter: Letter,
    painter: Painter = painterResource(letter.type.image),
    scale: Float = 1f,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Image(
            painter = painter,
            alignment = Alignment.Center,
            contentDescription = letter.letter.toString(),
            modifier = Modifier.requiredSize((30f * scale).dp)
        )
        Text(
            text = letter.letter.toString(),
            modifier = Modifier.align(Alignment.TopCenter),
            fontSize = TextUnit((17f * scale), TextUnitType.Sp),
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
            color = Color.LightGray
        )
        Text(
            text = letter.level.toString(),
            modifier = Modifier.align(Alignment.BottomCenter),
            fontSize = TextUnit((10f * scale), TextUnitType.Sp),
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
            color = Color.White
        )
    }
}