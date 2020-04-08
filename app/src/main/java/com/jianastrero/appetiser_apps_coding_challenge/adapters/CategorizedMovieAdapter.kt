package com.jianastrero.appetiser_apps_coding_challenge.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jianastrero.appetiser_apps_coding_challenge.R
import com.jianastrero.appetiser_apps_coding_challenge.databinding.ItemCategorizedMoviesBinding
import com.jianastrero.appetiser_apps_coding_challenge.models.Movie

class CategorizedMovieAdapter(
    private val categorizeMovies: List<Pair<String, List<Movie>>>
) : RecyclerView.Adapter<CategorizedMovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_categorized_movies,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = categorizeMovies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = categorizeMovies[holder.adapterPosition]

        holder.binding.title = item.first
    }

    class ViewHolder(val binding: ItemCategorizedMoviesBinding) :
        RecyclerView.ViewHolder(binding.root)
}