package co.softwarejourneys.moviesmp.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.softwarejourneys.moviesmp.MovieData
// Import the necessary Glide library
import androidx.recyclerview.widget.RecyclerView

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import co.softwarejourneys.movies.ui.movies.MovieListener
//import co.softwarejourneys.movies.ui.movies.ViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AllMoviesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

private const val TAG = "MoviesFragment" // Add a unique tag for your fragment
class AllMoviesFragment : Fragment(), MovieListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var movieRecyclerView: RecyclerView
    private val movieData = mutableListOf<MovieData>()  // Replace with your list of movies
    val movieViewModel: MoviesViewModel by viewModels(ownerProducer = { requireParentFragment() })


    override fun onMovieClicked(movieData: MovieData) {
        // Handle the movie click event here
        // For example, you can log the movie details:
        Log.d(TAG, "Movie clicked: ${movieData.title}")
        movieViewModel.updateMovie(movieData)
    }
    override fun onAttach(context: Context) {
        super.onAttach(requireContext()) // Use requireContext() instead of Context
        Log.d(TAG, "AllMoviesFragment:onAttach")
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        Log.d(TAG, "AllMoviesFragment:onCreate")
    }

    //private lateinit var movieImageView: ImageView
    //private lateinit var movieImageView2: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "AllMoviesFragment:onCreateView")
        val rootView = inflater.inflate(co.softwarejourneys.moviesmp.android.R.layout.fragment_all_movies, container, false)
        movieRecyclerView = rootView.findViewById(co.softwarejourneys.moviesmp.android.R.id.movieRecyclerView)
        movieRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        // Create an adapter for the RecyclerView


        // Optionally, set the layout manager for the RecyclerView
        //movieRecyclerView.layoutManager = LinearLayoutManager(context)
        //val movieViewModel: ViewModel by viewModels()
        //val movieViewModel: ViewModel by viewModels(ownerProducer = { requireParentFragment() })
        val adapter = MovieAdapter(this)
        movieRecyclerView.adapter = adapter
        movieViewModel.getMovies().observe(viewLifecycleOwner) { movies ->
            // Update the RecyclerView with the new list of movies
            adapter.setMovies(movies)
            adapter.notifyDataSetChanged()
        }

        // Load the movie image using Glide


        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "AllMoviesFragment:onViewCreated")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "AllMoviesFragment:onActivityCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "AllMoviesFragment:onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "AllMoviesFragment:onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "AllMoviesFragment:onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "AllMoviesFragment:onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "AllMoviesFragment:onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "AllMoviesFragment:onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "AllMoviesFragment:onDetach")
    }
}