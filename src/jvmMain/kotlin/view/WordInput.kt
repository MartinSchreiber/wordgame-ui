package view

import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.onPreviewKeyEvent

//TODO stylen
@Composable
fun WordInput(
    text: MutableState<String>, onKeyEvent: (KeyEvent) -> Boolean
) {
    val focusRequester = FocusRequester()
    TextField(
        value = text.value,
        onValueChange = { text.value = it },
        modifier = Modifier.onPreviewKeyEvent(onKeyEvent).focusRequester(focusRequester)
    )

    DisposableEffect(Unit) {
        focusRequester.requestFocus()
        onDispose { }
    }

}