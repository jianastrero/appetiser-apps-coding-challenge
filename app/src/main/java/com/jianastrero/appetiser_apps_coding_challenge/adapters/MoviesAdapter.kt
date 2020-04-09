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

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem.trackId == newItem.trackId

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem == newItem
    }
) {
    val currencyFormat = DecimalFormat("#,##0.00")

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

            currencyFormat.applyPattern("${item.currency} #,##0.00")

            holder.binding.price = currencyFormat.format(item.trackPrice)
            holder.binding.ivImage.clipToOutline = true

            holder.binding.root.setOnClickListener {
                onItemClickedListener.invoke(item)
            }

            item.artworkUrl100?.resize(200, "bb")?.into(holder.binding.ivImage)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setOnItemClickedListener(onItemClickedListener: (Movie) -> Unit) {
        this.onItemClickedListener = onItemClickedListener
    }

    class ViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)
}