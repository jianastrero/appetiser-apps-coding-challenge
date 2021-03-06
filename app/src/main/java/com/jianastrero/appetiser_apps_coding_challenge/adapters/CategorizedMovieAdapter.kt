package com.jianastrero.appetiser_apps_coding_challenge.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jianastrero.appetiser_apps_coding_challenge.R
import com.jianastrero.appetiser_apps_coding_challenge.databinding.ItemCategorizedMoviesBinding
import com.jianastrero.appetiser_apps_coding_challenge.models.Movie

class CategorizedMovieAdapter :
    ListAdapter<Pair<String, List<Movie>>, CategorizedMovieAdapter.ViewHolder>(
        object : DiffUtil.ItemCallback<Pair<String, List<Movie>>>() {

            // This is a diffutil, list adapters use this to check for differences from the old list
            // to the new list

            override fun areItemsTheSame(
                oldItem: Pair<String, List<Movie>>,
                newItem: Pair<String, List<Movie>>
            ): Boolean =
                oldItem.first == newItem.first

            override fun areContentsTheSame(
                oldItem: Pair<String, List<Movie>>,
                newItem: Pair<String, List<Movie>>
            ): Boolean =
                oldItem.first == newItem.first &&
                    oldItem.second == newItem.second
        }
    ) {

    // create a listener for item clicks
    private var onItemClickedListener: (Movie) -> Unit = {}

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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            val item = currentList[holder.adapterPosition]

            // set data
            holder.binding.title = item.first

            // submit the list of movies to the adapter
            holder.adapter.submitList(item.second)

            // set on item click listener
            holder.adapter.setOnItemClickedListener(onItemClickedListener)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * set a listener of item clicks
     *
     * @param onItemClickedListener Invoked when an item is clicked
     */
    fun setOnItemClickedListener(onItemClickedListener: (Movie) -> Unit) {
        this.onItemClickedListener = onItemClickedListener
    }

    class ViewHolder(val binding: ItemCategorizedMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val adapter = MoviesAdapter()

        init {
            // Set layout manager, adapter and disable nested scrolling
            binding.rvList.layoutManager = LinearLayoutManager(binding.root.context).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
            binding.rvList.adapter = adapter
            // disable this to make scrolling smooth
            binding.rvList.isNestedScrollingEnabled = false
        }
    }
}