package ru.nilsolk.weatherapptest.ui.weather_info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nilsolk.weatherapptest.databinding.HourlyItemBinding

class InfoAdapter(
    private val data: List<InfoItem>
) :
    RecyclerView.Adapter<InfoAdapter.InfoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HourlyItemBinding.inflate(inflater, parent, false)
        return InfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        val current = data[position]
        holder.bind(current)

    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class InfoViewHolder(private val binding: HourlyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(infoItem: InfoItem) {
            binding.apply {
                hour.text = infoItem.time
                hourImage.setImageResource(infoItem.image)
                hourTemperature.text = infoItem.temperature
            }
        }
    }
}
