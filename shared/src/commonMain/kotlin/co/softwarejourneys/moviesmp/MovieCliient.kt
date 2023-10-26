package co.softwarejourneys.moviesmp

import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.Url
import kotlinx.serialization.Serializable
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import kotlinx.coroutines.*


@Serializable
data class MovieResponse(
    @SerialName("results")
    val results: List<MovieData>,
    )



class MovieClient {

    private val apiKey = "3a5281e2130799fa6d7480a76eda35eb"
    private val apiUrl = Url("https://api.themoviedb.org/3/discover/movie?api_key=$apiKey")

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    /*prettyPrint = true
                    isLenient = true*/
                    ignoreUnknownKeys = true
                    useAlternativeNames = false
                }
            )
        }
    }

    suspend fun fetchMovies(): List<MovieData>{
        try {
            return createUrl(client.get(url = apiUrl).body<MovieResponse>().results)
        } catch (e: Exception) {
            // Handle the exception appropriately (e.g., log the error or throw it)
            e.printStackTrace()
            throw e
        }
    }

    fun createUrl(movies: List<MovieData>): List<MovieData> {
        val baseImgUrl = "https://image.tmdb.org/t/p"
        val size = "/w500"
        return movies.map { movie ->
            val url = "$baseImgUrl$size${movie.imageResourceId}"
            movie.copy(imageResourceId = url)
        }
    }
}