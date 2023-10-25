package co.softwarejourneys.moviesmp.ui.data

import android.content.Context
import co.softwarejourneys.moviesmp.DatabaseManager
import co.softwarejourneys.moviesmp.MovieData

class RoomDatabaseManager(private val context: Context):DatabaseManager {
    private val database: AppDatabase = DatabaseSingleton.getDatabase(context)
    private val utils = MovieUtils()
    override fun getMovies(): List<MovieData> {
        return utils.entityMovieListToDataMovieList(database.movieDao().getAllFavoriteMovies())
    }
    override fun saveMovie(movie: MovieData) {
        database.movieDao().insert(utils.dataMovieToEntity(movie))
        println(getMovies().toString())

    }
    override fun deleteMovie(movie: MovieData) {
        database.movieDao().deleteMovie(utils.dataMovieToEntity(movie))
        println(getMovies().toString())
    }
}