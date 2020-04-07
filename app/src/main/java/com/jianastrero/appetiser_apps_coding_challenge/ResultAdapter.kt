package com.jianastrero.appetiser_apps_coding_challenge

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jianastrero.appetiser_apps_coding_challenge.databinding.ItemResultBinding
import com.jianastrero.appetiser_apps_coding_challenge.models.Movie

class ResultAdapter(
    private val list: List<Movie>
) : RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_result,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[holder.adapterPosition]

        holder.binding.title = item.trackName
        holder.binding.price =
            "${item.currency} | ${item.trackPrice} | ${item.trackRentalPrice} | ${item.trackHdPrice} | ${item.trackHdRentalPrice}"
    }

    class ViewHolder(val binding: ItemResultBinding) : RecyclerView.ViewHolder(binding.root)
}