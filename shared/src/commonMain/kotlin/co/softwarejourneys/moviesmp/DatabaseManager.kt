package co.softwarejourneys.moviesmp

interface DatabaseManager {
    fun getMovies(): List<MovieData>
    fun saveMovie(movie: MovieData)
    fun deleteMovie(movie: MovieData)
}