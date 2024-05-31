package database

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import java.io.File

/**
 * ./gradlew desktopRun -DmainClass=MainKt --quiet
 */
fun getMoviesDatabase(): AppDatabase {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "movies.db")
    return Room.databaseBuilder<AppDatabase>(

        name = dbFile.absolutePath
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}