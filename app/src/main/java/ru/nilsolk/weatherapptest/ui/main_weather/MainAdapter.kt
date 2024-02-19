package ru.nilsolk.weatherapptest.ui.main_weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nilsolk.weatherapptest.databinding.DayItemBinding


class MainAdapter(
    private val data: List<MainItem>,
    private var itemClickListener: OnItemClickListener<MainItem>? = null
) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding =
            DayItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val current = data[position]
        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(current)
        }
        holder.bind(current)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setOnItemClickListener(listener: OnItemClickListener<MainItem>) {
        itemClickListener = listener
    }

    inner class MainViewHolder(private val binding: DayItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(mainItem: MainItem) {
            binding.apply {
                mainWeatherNow.text = mainItem.weatherNow
                mainWeekday.text = mainItem.weekday
                mainRecyclerImage.setImageResource(mainItem.weatherImage)
                minMaxMain.text = mainItem.minMaxTemp
            }
        }
    }
}


interface OnItemClickListener<T> {
    fun onItemClick(item: T)
}