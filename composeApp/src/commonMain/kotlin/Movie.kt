
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Movie(
    @PrimaryKey val id: Int,
    val poster: String,
    val title: String
)