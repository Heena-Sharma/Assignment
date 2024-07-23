
package com.ns.assignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ns.assignment.databinding.ListItemBinding
import com.ns.assignment.data.Data

class DataListAdapter(val isEmpty:(Boolean)-> Unit) : ListAdapter<Data, RecyclerView.ViewHolder>(
    RecordsDiffCallback()
),
    Filterable {
    private var data= listOf<Data>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RecordViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = getItem(position)
        (holder as RecordViewHolder).bind(data)
    }
    fun publishData(data: List<Data>) {
        this.data = data
        submitList(this.data)
    }
    class RecordViewHolder(
        private val binding: ListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Data) {
            binding.apply {

                bindImageFromUrl(ivRecord,item.mainImageURL)
                tvTitle.text= item.title
                tvDesc.text= item.shortDescription

            }
        }

    }

    override fun getFilter(): Filter {
        return FilterListData<Data>(data) {
            isEmpty(it.isEmpty())
            submitList(it)
        }
    }
}
class RecordsDiffCallback : DiffUtil.ItemCallback<Data>() {

    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem == newItem
    }
}

class FilterListData<T>(private val data: List<Data>, val onFilter: (List<T>) -> Unit) : Filter() {
    override fun performFiltering(text: CharSequence?): FilterResults {
        var filteredList = mutableListOf<Data>()
        if (text.isNullOrEmpty()) {
            filteredList = data.toMutableList()
        } else {
            for (i in data) {
                if (i.title.contains(text, ignoreCase = true))
                    filteredList.add(i)
            }
        }
        return FilterResults().apply { values = filteredList }
    }

    override fun publishResults(ch: CharSequence?, p1: FilterResults?) {
        try {
            onFilter(p1?.values as List<T>)
        } catch (e: Exception) {
            onFilter(listOf())
        }
    }

}
