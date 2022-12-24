package view.gameplay

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import model.Word
import view.navigation.AppState

@OptIn(ExperimentalUnitApi::class)
@Composable
fun WordInput(
    text: MutableState<String>,
    word: MutableState<Word>,
    onKeyEvent: (KeyEvent) -> Boolean,
    onValueChange: (String) -> Unit
) {
    val focusRequester = FocusRequester()

    Text(
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        text = word.value.toString(),
        fontSize = TextUnit(18f, TextUnitType.Sp),
        modifier = Modifier.fillMaxWidth()
    )

    TextField(
        readOnly = AppState.isPaused(),
        value = text.value,
        onValueChange = { onValueChange(it) },
        modifier = Modifier.onPreviewKeyEvent(onKeyEvent).focusRequester(focusRequester).fillMaxSize(0f)
    )

    DisposableEffect(Unit) {
        focusRequester.requestFocus()
        onDispose { }
    }

}