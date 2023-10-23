package co.softwarejourneys.moviesmp.ui.movies

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import co.softwarejourneys.moviesmp.MovieData
import com.bumptech.glide.Glide
import androidx.fragment.app.viewModels
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.softwarejourneys.movies.ui.movies.MovieListener
//import co.softwarejourneys.movies.ui.movies.ViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavoriteMoviesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoriteMoviesFragment : Fragment(), MovieListener {
    private fun updateUIWithSelectedMovie(selectedMovieData: MovieData, movieImageView: ImageView) {
        // Here, you can update your UI to display the selected movie.
        // For example, you can set the movie image, title, and any other relevant information.
        // You can access the image view and other UI elements to update them.
        // Replace the placeholders with actual UI elements in your layout.

        // Update the UI with the selected movie's data
        // For example:
        Glide.with(requireContext())
            .load(selectedMovieData.imageResourceId)
            .into(movieImageView)
        // You can similarly update other UI elements.
    }


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var movieRecyclerView: RecyclerView
    private val movieData = mutableListOf<MovieData>()
    val movieMoviesViewModel: MoviesViewModel by viewModels(ownerProducer = { requireParentFragment() })

    override fun onMovieClicked(movieData: MovieData) {
        // Handle the movie click event here
        // For example, you can log the movie details:
        Log.d("value", "Movie clicked: ${movieData.title}")
        movieMoviesViewModel.updateMovie(movieData)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(co.softwarejourneys.moviesmp.android.R.layout.fragment_favorite_movies, container, false)
        movieRecyclerView = rootView.findViewById(co.softwarejourneys.moviesmp.android.R.id.favoriteMovieRecyclerView)
        movieRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        //val movieViewModel: ViewModel by viewModels()
        // Initialize the ImageView

        //val movieViewModel: ViewModel by viewModels(ownerProducer = { requireParentFragment() })
        val adapter = MovieAdapter(this)
        movieRecyclerView.adapter = adapter
        movieMoviesViewModel.getFavoriteMovies().observe(viewLifecycleOwner) { movies ->
            // Update the RecyclerView with the new list of movies
            adapter.setMovies(movies)

            adapter.notifyDataSetChanged()
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("FavoriteMovies", "FavoriteMoviesFragment:onViewCreated")
        val movieMoviesViewModel: MoviesViewModel by viewModels(ownerProducer = { requireParentFragment() })

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FavoriteMoviesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavoriteMoviesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}