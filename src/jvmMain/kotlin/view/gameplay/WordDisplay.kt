package view.gameplay

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import constants.LetterType
import model.Letter
import model.Word

@Composable
fun WordDisplay(word: Word) {
    val painterMap = LetterType.values().associateWith { painterResource(it.image) }
    Row {
        word.letters.forEach {
            LetterDisplay(it, painterMap[it.type]!!)
        }
    }
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun LetterDisplay(
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
            fontSize = TextUnit((22.5f * scale), TextUnitType.Sp),
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
            color = Color.LightGray
        )
    }
}