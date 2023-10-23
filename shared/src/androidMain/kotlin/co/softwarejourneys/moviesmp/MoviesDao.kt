package co.softwarejourneys.moviesmp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MoviesDao  {

    @Insert
    fun insert(movie: MovieEntity)

    @Query("SELECT * FROM movies")
    fun getAllFavoriteMovies(): List<MovieEntity>

    @Delete
    fun deleteMovie(movie: MovieEntity)

    // Add other queries and operations
}
