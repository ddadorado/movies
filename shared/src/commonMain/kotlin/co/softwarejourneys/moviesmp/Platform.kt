package co.softwarejourneys.moviesmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform