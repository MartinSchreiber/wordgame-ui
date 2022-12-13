package view.components

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun SimpleButton(onClick: () -> Unit, text: String) {
    Button(onClick = onClick) {
        Text(text = text)
    }
}