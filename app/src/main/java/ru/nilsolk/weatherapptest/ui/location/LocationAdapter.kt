package ru.nilsolk.weatherapptest.ui.location

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nilsolk.weatherapptest.R
import ru.nilsolk.weatherapptest.databinding.LocationItemBinding
import ru.nilsolk.weatherapptest.ui.main_weather.MainItem
import ru.nilsolk.weatherapptest.ui.main_weather.OnItemClickListener

class LocationAdapter(
    private var data: List<LocationItem>,
    private var itemClickListener: OnItemClickListener<LocationItem>? = null
) :
    RecyclerView.Adapter<LocationAdapter.SearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding =
            LocationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    fun setOnItemClickListener(listener: OnItemClickListener<LocationItem>) {
        itemClickListener = listener
    }
    fun updateList(newData: List<LocationItem>) {
        data = newData
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val current = data[position]
        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(current)
        }
        holder.bind(current)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class SearchViewHolder(private val binding: LocationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(locationItem: LocationItem) {
            binding.city.text = locationItem.city
            binding.country.text = locationItem.country
        }
    }
}