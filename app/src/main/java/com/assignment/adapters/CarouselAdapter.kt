package com.assignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.assignment.data.Records
import com.ns.assignment.databinding.ItemImageCarouselBinding


class CarouselAdapter : ListAdapter<Records, RecyclerView.ViewHolder>(CarouselDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CarouselViewholder(
            ItemImageCarouselBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val records = getItem(position)
        (holder as CarouselViewholder).bind(records)

    }

    class CarouselViewholder(
        private val binding: ItemImageCarouselBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Records) {
            binding.apply {
                data = item
                executePendingBindings()
            }
        }

    }
}
 class CarouselDiffCallback : DiffUtil.ItemCallback<Records>() {

    override fun areItemsTheSame(oldItem: Records, newItem: Records): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Records, newItem: Records): Boolean {
        return oldItem == newItem
    }
}


