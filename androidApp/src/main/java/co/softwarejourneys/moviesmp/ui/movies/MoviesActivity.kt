package co.softwarejourneys.moviesmp.ui.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.softwarejourneys.moviesmp.android.R
//import co.softwarejourneys.movies.ui.movies.MoviesFragment

class MoviesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_movies)

        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_content_movies, MoviesFragment())
            .commit()
    }

}
