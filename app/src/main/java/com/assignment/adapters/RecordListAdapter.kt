
package com.assignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.assignment.data.Records
import com.ns.assignment.databinding.RecordsListItemBinding

class RecordListAdapter(val isEmpty:(Boolean)-> Unit) : ListAdapter<Records, RecyclerView.ViewHolder>(
    RecordsDiffCallback()
),
    Filterable {
    var data= listOf<Records>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RecordViewHolder(
            RecordsListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val records = getItem(position)
        (holder as RecordViewHolder).bind(records)
    }
    fun publishData(data: List<Records>) {
        this.data = data
        submitList(this.data)
    }
    class RecordViewHolder(
        private val binding: RecordsListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Records) {
            binding.apply {
                records = item
                executePendingBindings()
            }
        }

    }

    override fun getFilter(): Filter {
        return FilterListData<Records>(data) {
            isEmpty(it.isEmpty())
            submitList(it)
        }
    }
}
class RecordsDiffCallback : DiffUtil.ItemCallback<Records>() {

    override fun areItemsTheSame(oldItem: Records, newItem: Records): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Records, newItem: Records): Boolean {
        return oldItem == newItem
    }
}

class FilterListData<T>(private val data: List<Records>, val onFilter: (List<T>) -> Unit) : Filter() {
    override fun performFiltering(text: CharSequence?): FilterResults {
        var filteredList = mutableListOf<Records>()
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
