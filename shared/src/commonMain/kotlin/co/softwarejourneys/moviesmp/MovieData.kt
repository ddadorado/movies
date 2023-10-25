package co.softwarejourneys.moviesmp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MovieData(
    @SerialName("backdrop_path")
    val imageResourceId: String,
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("vote_average")
    val rating: Double,
    var isFavorite: Boolean = false
)
{
    /*fun MovieData.toEntity(): MovieEntity {
        return MovieEntity(
            id = this.id,
            name = this.title,
            url = this.imageResourceId,
            rating = this.rating,
            isFavorite = this.isFavorite
        )
    }*/
}