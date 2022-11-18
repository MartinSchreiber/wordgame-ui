import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import control.backgroundTasks
import kotlinx.coroutines.launch
import model.WordGame
import view.WordGame


//TODO implement menu, boiler etc.
fun main() = application {
    val scope = rememberCoroutineScope()

    val wordGame = WordGame()
    val backgroundJob = scope.launch {
        backgroundTasks(wordGame)
    }


    Window(onCloseRequest = ::exitApplication) {
        WordGame(wordGame = wordGame)
    }
    backgroundJob.cancel(null)
}