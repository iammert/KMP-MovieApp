import android.app.Application
import database.AppDatabase
import org.example.movieapp.App.Companion.requireInstance
import org.example.movieapp.database.getMoviesDatabase

actual class AppFactory(val application: Application) {
    actual fun getDatabase(): AppDatabase {
        return getMoviesDatabase(application)
    }
}

actual val appFactoryInstance: AppFactory by lazy { AppFactory(requireInstance()) }