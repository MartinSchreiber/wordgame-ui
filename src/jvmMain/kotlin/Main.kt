import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import control.backgroundTasks
import model.WordGame
import view.WordGame

//TODO implement menu, boiler etc.
fun main() = application {

    //TODO extract call to background-tasks and Composable into function?
    val wordGame = WordGame()
    backgroundTasks(wordGame)

    Window(onCloseRequest = ::exitApplication) {
        WordGame(wordGame = wordGame)
    }
}