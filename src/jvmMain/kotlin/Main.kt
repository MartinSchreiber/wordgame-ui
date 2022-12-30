import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import view.navigation.AppState
import view.navigation.AppUI

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = AppState.translate("window_title"),
        resizable = false,
        state = WindowState(
            position = WindowPosition(Alignment.Center),
            size = DpSize(width = 1024f.dp, height = 768f.dp)
        )
    ) {
        AppUI()
    }
}