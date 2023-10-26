package co.softwarejourneys.moviesmp.ui.data

import co.softwarejourneys.moviesmp.MovieData
import co.softwarejourneys.moviesmp.MovieEntity

class MovieUtils {

    fun dataMovieToEntity(movieData: MovieData): MovieEntity {
        return MovieEntity(movieData.id, movieData.title, movieData.imageResourceId, movieData.rating, movieData.isFavorite)
    }

    fun entityToDataMovie(movieEntity: MovieEntity) : MovieData {
        return MovieData(movieEntity.url, movieEntity.id, movieEntity.name, movieEntity.rating, movieEntity.isFavorite)
    }

    fun entityMovieListToDataMovieList(entityList: List<MovieEntity>): List<MovieData> =
        entityList.map { entityToDataMovie(it) }

    fun mergeFavoriteListToGeneral(all: List<MovieData>, favorites: List<MovieData>): List<MovieData> {
        if (favorites.isEmpty()) {
            return all
        }
        val out = favorites.toMutableList()
        out.addAll(all.filter { movie -> findByID(favorites, movie.id) })
        return out
    }

    fun createUrl(movies: List<MovieData>): List<MovieData> {
        val baseImgUrl = "https://image.tmdb.org/t/p"
        val size = "/w500"
        return movies.map { movie ->
            val url = "$baseImgUrl$size${movie.imageResourceId}"
            movie.copy(imageResourceId = url)
        }
    }


    private fun findByID(lista: List<MovieData>, idBuscado: Int): Boolean {
        return lista.find { it.id == idBuscado } == null
        }
}