package com.example.movies.ui.main.actors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.domain.model.actor.Actor
import com.example.movies.ui.main.UrlDataPath

class ActorsAdapter(private val itemClicked: (actor: Actor) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data = mutableListOf<Actor>()

    fun setData(dataToSet: List<Actor>) {
        data.apply {
            clear()
            addAll(dataToSet)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ActorsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_actor, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ActorsViewHolder).bind(data[position])
    }

    override fun getItemCount(): Int = data.size


    inner class ActorsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image = itemView.findViewById<ImageView>(R.id.actor_image_view)
        private val name = itemView.findViewById<TextView>(R.id.actor_name_text_view)
        private val rating = itemView.findViewById<RatingBar>(R.id.actor_item_ratingBar)

        init {
            itemView.setOnClickListener {
                itemClicked.invoke(data[adapterPosition])
            }
        }

        fun bind(actor: Actor) {
            Glide.with(itemView)
                .load(actor.profilePath?.let { UrlDataPath.getPosterPath(it) })
                .placeholder(R.drawable.avatar_background)
                .error(R.drawable.avatar_background)
                .into(image)
            name.text = actor.name
            rating.rating = actor.popularity.toFloat() / 2

        }
    }
}