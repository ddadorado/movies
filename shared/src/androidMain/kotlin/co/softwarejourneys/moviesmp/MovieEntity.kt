package co.softwarejourneys.moviesmp
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    val id: Int = 0,
    val name: String,
    val url: String,
    val rating : Double,
    val isFavorite: Boolean
)
