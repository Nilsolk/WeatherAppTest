package ru.nilsolk.weatherapptest.ui.weather_info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nilsolk.weatherapptest.ImageLoader
import ru.nilsolk.weatherapptest.databinding.HourlyItemBinding
import ru.nilsolk.weatherapptest.ui.main_weather.MainItem

class InfoAdapter(
    private val imageLoader: ImageLoader
) :
    RecyclerView.Adapter<InfoAdapter.InfoViewHolder>() {
    private var data: List<InfoItem> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HourlyItemBinding.inflate(inflater, parent, false)
        return InfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        val current = data[position]
        holder.bind(current)

    }

    fun updateList(newData: List<InfoItem>) {
        data = newData
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class InfoViewHolder(private val binding: HourlyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(infoItem: InfoItem) {
            binding.apply {
                hour.text = infoItem.time
                imageLoader.loadImage(infoItem.image, hourImage)
                hourTemperature.text = infoItem.temperature
            }
        }
    }
}
