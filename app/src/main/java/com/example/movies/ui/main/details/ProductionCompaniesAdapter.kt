package com.example.movies.ui.main.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.domain.model.movie.ProductionCompanies
import com.example.movies.ui.main.UrlDataPath

class ProductionCompaniesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data = mutableListOf<ProductionCompanies>()

    fun setData(dataToSet: List<ProductionCompanies>) {
        data.apply {
            clear()
            addAll(dataToSet)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProductionCompaniesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_production_company, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ProductionCompaniesViewHolder).bind(data[position])
    }


    override fun getItemCount(): Int {
        return data.size
    }

    inner class ProductionCompaniesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val icon = itemView.findViewById<ImageView>(R.id.company_icon_ImageView)
        fun bind(productionCompanies: ProductionCompanies) {
            Glide.with(itemView)
                .load(productionCompanies.logoPath?.let { UrlDataPath.getPosterPath(it) })
                .into(icon)
        }

    }
}