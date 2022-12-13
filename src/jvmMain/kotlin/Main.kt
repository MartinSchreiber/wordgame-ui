import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import view.navigation.AppUI

fun main() = application {

    Window(onCloseRequest = ::exitApplication, title = "WordGame") {
        AppUI()
    }
}