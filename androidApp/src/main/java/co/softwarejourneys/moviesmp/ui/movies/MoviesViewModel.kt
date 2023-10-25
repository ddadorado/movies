package co.softwarejourneys.moviesmp.ui.movies

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import co.softwarejourneys.moviesmp.MovieData
import co.softwarejourneys.moviesmp.ui.data.MovieUtils
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import co.softwarejourneys.moviesmp.MovieRepository
import co.softwarejourneys.moviesmp.ui.data.RoomDatabaseManager


class MoviesViewModel(): ViewModel() {

    private val moviesList = MutableLiveData<List<MovieData>>()
    lateinit var repository: MovieRepository
    val utils = MovieUtils()

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
                    repository.saveMovie(updatedMovieData)
                }
            }
        } else {
            viewModelScope.launch {
                withContext(IO) {
                    repository.deleteMovie(updatedMovieData)
                }
            }
        }
        val currentMovies = moviesList.value ?: return
        val updatedMovies = currentMovies?.map { movie ->
            if (movie.id == updatedMovieData.id) {
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

            repository = MovieRepository(RoomDatabaseManager(application))
            viewModelScope.launch {
                try {
                    val movies = withContext(Dispatchers.IO) {
                        repository.fetchMovies()
                    }
                    var favoriteMoviesDB = withContext(IO) {
                        repository.getMovies()
                    }
                    setMovies(utils.mergeFavoriteListToGeneral(movies, favoriteMoviesDB))

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}