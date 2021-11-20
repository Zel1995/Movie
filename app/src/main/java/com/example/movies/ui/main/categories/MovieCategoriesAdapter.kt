package com.example.movies.ui.main.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.domain.model.movie.Movie
import com.example.movies.domain.model.movie.MovieCategory

typealias ItemClicked = (movie: Movie) -> Unit

class MovieCategoriesAdapter(
    private val itemClicked: ItemClicked
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data = mutableListOf<MovieCategory>()

    fun setData(dataToSet: List<MovieCategory>) {
        data.apply {
            clear()
            addAll(dataToSet)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieCategoriesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie_category, parent, false),
            itemClicked
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]
        (holder as? MovieCategoriesViewHolder)?.apply {
            categoryName.text = item.name
            with(categoryListRv) {

                val lm =
                    LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
                layoutManager = lm

                adapter = MoviesAdapter(item.results) {
                    itemClicked.invoke(it)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class MovieCategoriesViewHolder(itemView: View, itemClicked: ItemClicked? = null) :
        RecyclerView.ViewHolder(itemView) {
        val categoryName: TextView = itemView.findViewById(R.id.tv_category)
        val categoryListRv: RecyclerView = itemView.findViewById(R.id.rv_category)

    }
}