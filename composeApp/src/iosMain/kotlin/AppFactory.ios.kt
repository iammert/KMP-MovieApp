import database.AppDatabase
import database.getMoviesDatabase

actual class AppFactory {
    actual fun getDatabase(): AppDatabase {
        return getMoviesDatabase()
    }
}

actual val appFactoryInstance: AppFactory by lazy { AppFactory() }