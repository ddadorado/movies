package co.softwarejourneys.moviesmp.ui.movies
import android.app.Application
import android.util.Log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import co.softwarejourneys.moviesmp.MovieClient
import co.softwarejourneys.moviesmp.MovieData
import co.softwarejourneys.moviesmp.ui.data.MovieUtils
//import co.softwarejourneys.moviesmp.ui.movies.Movie
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext


class MoviesViewModel: ViewModel() {
    private val selectedMovieMutableLiveDataData = MutableLiveData<MovieData>()
    val selectedMovieLiveDataData: LiveData<MovieData> get() = selectedMovieMutableLiveDataData
    private val moviesList = MutableLiveData<List<MovieData>>()
    val greetings = MovieClient()
    lateinit var repository : MovieRepository
    val utils = MovieUtils()


    fun setSelectedMovie(movieData: MovieData) {
        selectedMovieMutableLiveDataData.value = movieData
        val movies = moviesList.value
        if (movies != null) {
            Log.d("ViewModel", "Movies set in ViewModel: setted ${movies.joinToString { it.title }}")
        }
    }


    fun setMovies(movieData: List<MovieData>) {
        moviesList.value = movieData
        Log.d("ViewModel", "Movies set in ViewModel: ${movieData.joinToString { it.title }}")
    }

    fun getMovies(): LiveData<List<MovieData>> {
        return moviesList
    }

    fun getFavoriteMovies(): LiveData<List<MovieData>> {
        val favoriteMovies = MediatorLiveData<List<MovieData>>()
        favoriteMovies.addSource(moviesList) { movies ->
            favoriteMovies.value = movies.filter { it.isFavorite }
        }
        return favoriteMovies
    }

    fun updateMovie(updatedMovieData: MovieData) {
        if (updatedMovieData.isFavorite) {
            viewModelScope.launch {
                withContext(IO) {
                    repository.saveMovie(utils.dataMovieToEntity(updatedMovieData))
                }
            }
        } else {
            viewModelScope.launch {
                withContext(IO) {
                    repository.deleteMovie(utils.dataMovieToEntity(updatedMovieData))
                }
            }
        }
        val currentMovies2 = moviesList.value ?: return
        val updatedMovies = currentMovies2?.map { movie ->
            if (movie.id == updatedMovieData.id) {
                // Replace the old movie with the updated movie
                updatedMovieData
            } else {
                movie
            }
        }
        // Update the LiveData with the new list of movies
        setMovies(updatedMovies!!)
    }


    fun initialize(application: Application) {
        try {
            Log.d("ViewModel", "greeting CreatedNo")
            println("ViewModel greeting CreatedNo")
            repository = MovieRepository(application)
            viewModelScope.launch {
                try {
                    val movies = withContext(Dispatchers.IO) {
                        greetings.fetchMovies()

                    }
                    println("fetched movies")
                    var favoriteMoviesDB = withContext(IO){
                        repository.getMovies()
                    }
                    setMovies(utils.mergeFavoriteListToGeneral(movies, favoriteMoviesDB))
                    repository.setMovies(utils.mergeFavoriteListToGeneral(movies, favoriteMoviesDB))
                    Log.d("ViewModel", "greeting fetching movies $movies")
                    println("greeting fetching movies 2")
                } catch (e: Exception) {
                    Log.d("ViewModel", "greeting fetching movies error ${e.message}")
                    e.printStackTrace()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}