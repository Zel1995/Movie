package com.example.movies.ui.main.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.domain.model.movie.Movie
import com.example.movies.ui.main.UrlDataPath

class MoviesAdapter(
    private val movies: List<Movie>,
    private val onMovieClicked: (movie: Movie) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(root, onMovieClicked)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MovieViewHolder).bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class MovieViewHolder(itemView: View, onMovieClicked: (movie: Movie) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.movie_image_view)
        val movieTitle = itemView.findViewById<TextView>(R.id.movie_title_tv)

        fun bind(movie: Movie) {
            Glide.with(itemView)
                .load(movie.posterPath?.let { UrlDataPath.getPosterPath(it) })
                .placeholder(R.drawable.movie_background3)
                .error(R.drawable.movie_background3)
                .into(image)
            movieTitle.text = movie.title
        }

        init {
            itemView.setOnClickListener { onMovieClicked.invoke(movies[adapterPosition]) }
        }
    }
}