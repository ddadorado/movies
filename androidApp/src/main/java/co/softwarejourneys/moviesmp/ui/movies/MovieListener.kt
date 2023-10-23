package co.softwarejourneys.movies.ui.movies

import co.softwarejourneys.moviesmp.MovieData

interface MovieListener {
    fun onMovieClicked(movieData: MovieData)
}