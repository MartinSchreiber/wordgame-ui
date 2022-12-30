package view.test

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import constants.LetterType
import constants.lettervalues.LetterValueEnglish
import model.Letter
import view.components.ScrollableBox

@Composable
@Preview
fun Test() {
    ScrollableBox(modifier = Modifier.fillMaxSize().background(color = Color.DarkGray)) {
        TestLetter(Letter('Q'))
        LetterValueEnglish.values().forEach {
            TestLetter(Letter(it.letter))
        }
    }
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun TestLetter(letter: Letter) {
    val painterMap = LetterType.values().associateWith { painterResource(it.image) }
    Box(modifier = Modifier.background(color = Color.DarkGray)) {
        Image(
            painter = painterMap[letter.type]!!,
            alignment = Alignment.Center,
            contentDescription = null, //TODO
            modifier = Modifier.requiredSize(30f.dp)
        )
        Text(
            text = letter.letter.toString(),
            modifier = Modifier.align(Alignment.TopCenter),//offset(x = 60f.dp, y = (-30f).dp),
            fontSize = TextUnit(22.5f, TextUnitType.Sp),
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
            color = Color.LightGray
        )
    }

}