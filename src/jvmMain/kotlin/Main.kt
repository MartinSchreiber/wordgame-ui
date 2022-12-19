import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import util.PersistenceUtil
import view.navigation.AppState
import view.navigation.AppUI

fun main() = application {
    PersistenceUtil.initDir()

    Window(onCloseRequest = ::exitApplication, title = AppState.translate("window_title")) {
        AppUI()
    }
}