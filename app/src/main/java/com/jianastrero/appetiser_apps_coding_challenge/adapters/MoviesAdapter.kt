package com.jianastrero.appetiser_apps_coding_challenge.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jianastrero.appetiser_apps_coding_challenge.R
import com.jianastrero.appetiser_apps_coding_challenge.databinding.ItemMovieBinding
import com.jianastrero.appetiser_apps_coding_challenge.extensions.into
import com.jianastrero.appetiser_apps_coding_challenge.extensions.resize
import com.jianastrero.appetiser_apps_coding_challenge.models.Movie
import java.text.DecimalFormat

class MoviesAdapter : ListAdapter<Movie, MoviesAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<Movie>() {

        // This is a diffutil, list adapters use this to check for differences from the old list
        // to the new list

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem.trackId == newItem.trackId

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem == newItem
    }
) {
    // default currency format
    val currencyFormat = DecimalFormat("#,##0.00")

    // create a listener for item clicks
    private var onItemClickedListener: (Movie) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_movie,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            val item = currentList[holder.adapterPosition]

            holder.binding.title = item.trackName

            // reset the currency format pattern using the current movies currency
            currencyFormat.applyPattern("${item.currency} #,##0.00")

            // Set data
            holder.binding.price = currencyFormat.format(item.trackPrice)
            holder.binding.ivImage.clipToOutline = true

            // Invoke item click listener when an item is clicked
            holder.binding.root.setOnClickListener {
                onItemClickedListener.invoke(item)
            }

            // Set a 200x22 image on the image view
            item.artworkUrl100?.resize(200, "bb")?.into(holder.binding.ivImage)
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

    class ViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)
}