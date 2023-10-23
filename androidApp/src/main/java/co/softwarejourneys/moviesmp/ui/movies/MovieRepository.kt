package co.softwarejourneys.moviesmp.ui.movies

import co.softwarejourneys.moviesmp.MovieClient
import kotlinx.coroutines.runBlocking
import android.content.Context
import co.softwarejourneys.moviesmp.MovieData
import co.softwarejourneys.moviesmp.MovieEntity
import co.softwarejourneys.moviesmp.ui.data.AppDatabase
import co.softwarejourneys.moviesmp.ui.data.DatabaseSingleton
import co.softwarejourneys.moviesmp.ui.data.MovieUtils

open class MovieRepository(private val context: Context) {
    open var moviesList: List<MovieData> = listOf()
    private val database: AppDatabase = DatabaseSingleton.getDatabase(context)
    private val  utils = MovieUtils()

    fun saveMovie(movie: MovieEntity){
        database.movieDao().insert(movie)
        println(getMovies().toString())
    }

    fun setMovies(movieData: List<MovieData>) {
        moviesList = movieData
        println("Movies set in ViewModel: ${movieData.joinToString { it.title }}")

    }
    open fun getMovies(): List<MovieData> {
        return utils.entityMovieListToDataMovieList(database.movieDao().getAllFavoriteMovies())
    }
    fun createMovies(){
        runBlocking {val movieClient = MovieClient()
            movieClient.fetchMovies()
            println("greetingCreated")
        }
    }

    open fun deleteMovie(movie: MovieEntity){
        database.movieDao().deleteMovie(movie)
        println(getMovies().toString())
    }


    /*private suspend fun retrieveMoviesFromApi(): List<Movie> = withContext(Dispatchers.IO) {
        // Here you can use a library like Ktor, Retrofit, or any other to fetch movies from the API
        // Implement your logic to fetch movies asynchronously and return them
        // For example:
        // val apiService = createApiService()
        // return apiService.getMovies()
        // Make sure to handle API requests asynchronously using withContext
    }*/
    init {



    }
}