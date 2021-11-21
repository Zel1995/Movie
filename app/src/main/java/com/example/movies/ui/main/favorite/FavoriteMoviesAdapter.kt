package com.example.movies.ui.main.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.domain.model.movie.Movie
import com.example.movies.ui.main.categories.MoviesAdapter.Companion.BASE_IMAGE_URL

class FavoriteMoviesAdapter(private val onItemClick:(movie: Movie)->Unit,private val onDeleteClick: (movie: Movie) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data = mutableListOf<Movie>()

    fun setData(dataToSet: List<Movie>) {
        data.apply {
            clear()
            addAll(dataToSet)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FavoriteMovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_favorite_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as FavoriteMovieViewHolder).bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class FavoriteMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image = itemView.findViewById<ImageView>(R.id.favorite_movie_image_view)
        private val title = itemView.findViewById<TextView>(R.id.favorite_title_text_view)
        private val content = itemView.findViewById<TextView>(R.id.favorite_content_text_view)
        private val likeIcon = itemView.findViewById<ImageView>(R.id.liked_icon_image_view)
        private val viewClick = itemView.findViewById<View>(R.id.click_view)
        init {
            likeIcon.setOnClickListener{
                onDeleteClick.invoke(data[adapterPosition])
            }
            viewClick.setOnClickListener{
                onItemClick.invoke(data[adapterPosition])
            }
        }
        fun bind(movie: Movie){
            Glide.with(itemView)
                .load(BASE_IMAGE_URL + movie.posterPath)
                .into(image)
            title.text = movie.title
            content.text = movie.overview
        }
    }
}