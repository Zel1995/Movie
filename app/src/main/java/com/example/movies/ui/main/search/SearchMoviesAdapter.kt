package com.example.movies.ui.main.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.domain.model.movie.Movie
import com.example.movies.ui.main.categories.MoviesAdapter.Companion.BASE_IMAGE_URL

class SearchMoviesAdapter(private val itemClicked: (Movie) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data = mutableListOf<Movie>()
    fun setData(dataToSet: List<Movie>) {
        data.apply {
            clear()
            addAll(dataToSet)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SearchMoviesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_search_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SearchMoviesViewHolder).bind(data[position])

    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class SearchMoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title = itemView.findViewById<TextView>(R.id.title_text_view)
        private val image = itemView.findViewById<ImageView>(R.id.search_movie_image_view)
        private val date = itemView.findViewById<TextView>(R.id.date_text_view)

        init {
            itemView.setOnClickListener {
                itemClicked.invoke(data[adapterPosition])
            }
        }

        fun bind(movie: Movie) {
            Glide.with(itemView)
                .load(BASE_IMAGE_URL + movie.posterPath)
                .placeholder(R.drawable.movie_background3)
                .error(R.drawable.movie_background3)
                .into(image)
            title.text = movie.title
            date.text = movie.releaseDate
        }

    }
}