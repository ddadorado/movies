package co.softwarejourneys.moviesmp.ui.movies
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.RatingBar
import android.widget.ToggleButton
import android.view.ViewGroup
import android.view.LayoutInflater
import co.softwarejourneys.moviesmp.MovieData
import co.softwarejourneys.movies.ui.movies.MovieListener
import com.bumptech.glide.Glide



class MovieAdapter(private val clickListener: MovieListener) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private val movieData: MutableList<MovieData> = mutableListOf()
    fun setMovies(movieData: List<MovieData>) {
        this.movieData.clear()
        this.movieData.addAll(movieData)
        notifyDataSetChanged()
    }
    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val movieImageView: ImageView = view.findViewById(co.softwarejourneys.moviesmp.android.R.id.movieImage)
        val movieTitleView: TextView = view.findViewById(co.softwarejourneys.moviesmp.android.R.id.movieTitle)
        val movieRatingBar: RatingBar = view.findViewById(co.softwarejourneys.moviesmp.android.R.id.movieRating)
        val markAsFavoriteButton: ToggleButton = view.findViewById(co.softwarejourneys.moviesmp.android.R.id.markAsFavoriteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(co.softwarejourneys.moviesmp.android.R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieData[position]
        val baseImgUrl = "https://image.tmdb.org/t/p"
        val size = "/w500"
        Glide.with(holder.itemView)
            .load("$baseImgUrl$size${movie.imageResourceId}")
            .into(holder.movieImageView)


        holder.movieTitleView.text = movie.title
        holder.movieRatingBar.rating = movie.rating.toFloat()/2
        holder.markAsFavoriteButton.isChecked = movie.isFavorite
        holder.markAsFavoriteButton.setOnClickListener {
            // Toggle the isFavorite property of the movie
            movie.isFavorite = !movie.isFavorite
            Log.d("markAsFavoriteButton", "markAsFavoriteButton:${movie.isFavorite}")
            clickListener.onMovieClicked(movie)
        }
    }

    override fun getItemCount(): Int {
        return movieData.size
    }
}
