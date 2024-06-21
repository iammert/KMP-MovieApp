import androidx.compose.ui.window.ComposeUIViewController
import database.getMoviesDatabase

fun MainViewController() = ComposeUIViewController { App(getMoviesDatabase()) }