package view.gameplay

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.unit.dp
import model.Word
import view.navigation.AppState

@Composable
fun WordInput(
    text: MutableState<String>,
    word: MutableState<Word>,
    onKeyEvent: (KeyEvent) -> Boolean,
    onValueChange: (String) -> Unit
) {
    val focusRequester = FocusRequester()

    Row(modifier = Modifier.height(30f.dp)) {
        WordDisplay(word.value)
    }

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