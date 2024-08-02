import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import database.AppDatabase
import org.jetbrains.compose.ui.tooling.preview.Preview
import player.AudioPlayer
import player.PlayerState

@Composable
@Preview
fun App() {
    MaterialTheme {

        val navController = rememberNavController()

        val playerState = remember { PlayerState() }

        val player = remember { AudioPlayer(playerState) }

        NavHost(navController, "movies") {
            composable("movies") {
                val viewModel =
                    viewModel { MoviesViewModel(appFactoryInstance.getDatabase().getDao()) }
                val uiState by viewModel.uiState.collectAsState()

                LaunchedEffect(viewModel) {
                    viewModel.updateMovies()
                }

                MoviesScreen(uiState)

                LaunchedEffect(Unit){
                    player.setSongUrl("https://download.samplelib.com/mp3/sample-3s.mp3")
                }

                Text("Is Playing: ${playerState.isPlaying}", fontSize = 22.sp)

            }
        }
    }
}

@Composable
private fun MoviesScreen(uiState: MoviesUiState, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(180.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxSize()
    ) {
        items(uiState.movies) { movie ->
            MovieCell(movie)
        }
    }
}

@Composable
private fun MovieCell(movie: Movie, modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        AsyncImage(
            movie.poster,
            contentDescription = movie.title,
            modifier = Modifier.fillMaxWidth().aspectRatio(2 / 3f).clip(RoundedCornerShape(16.dp))
        )
        Text(movie.title, textAlign = TextAlign.Center, style = MaterialTheme.typography.h6)
    }
}