package view.gameplay

import androidx.compose.foundation.layout.Row
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
import model.Word

@Composable
fun WordInput(
    text: MutableState<String>,
    word: MutableState<Word>,
    onKeyEvent: (KeyEvent) -> Boolean,
    onValueChange: (String) -> Unit
) {
    val focusRequester = FocusRequester()

    Row {
        TextField(
            value = text.value,
            onValueChange = { onValueChange(it) },
            modifier = Modifier.onPreviewKeyEvent(onKeyEvent).focusRequester(focusRequester)
        )
        Text(text = word.value.toString())
    }

    DisposableEffect(Unit) {
        focusRequester.requestFocus()
        onDispose { }
    }

}