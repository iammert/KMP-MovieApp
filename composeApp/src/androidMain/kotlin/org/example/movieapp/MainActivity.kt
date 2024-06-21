package org.example.movieapp

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.example.movieapp.database.getMoviesDatabase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App(getMoviesDatabase(this@MainActivity.applicationContext))
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    //App(getMoviesDatabase())
}