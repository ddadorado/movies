package co.softwarejourneys.moviesmp
open class MovieRepository(private val IdbManager: DatabaseManager) {

    val client = MovieClient()
    lateinit var listMovies: List<MovieData>

    suspend fun fetchMovies(): List<MovieData>{
        listMovies = client.fetchMovies()
        return  listMovies
    }

    fun getMovies(): List<MovieData> {
        return IdbManager.getMovies()
    }

    fun saveMovie(movie: MovieData){
        IdbManager.saveMovie(movie)
    }

    fun deleteMovie(movie: MovieData){
        IdbManager.deleteMovie(movie)
    }
}
