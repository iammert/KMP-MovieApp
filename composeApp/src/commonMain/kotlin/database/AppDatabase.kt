package database

import Movie
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Database(entities = [Movie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao(): MovieDao
}

@Dao
interface MovieDao {

    @Transaction
    suspend fun refreshMovies(item: List<Movie>){
        deleteAll()
        insertAll(item)
    }

    @Query("DELETE FROM Movie")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(item: List<Movie>)

    @Query("SELECT * FROM Movie")
    fun getAllAsFlow(): Flow<List<Movie>>
}