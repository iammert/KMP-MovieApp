import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import database.getMoviesDatabase

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Movie App") {
        App(getMoviesDatabase())
    }
}