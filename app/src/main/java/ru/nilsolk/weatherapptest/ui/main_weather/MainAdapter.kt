package ru.nilsolk.weatherapptest.ui.main_weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nilsolk.weatherapptest.data_source.ImageLoader
import ru.nilsolk.weatherapptest.databinding.DayItemBinding


class MainAdapter(
    private val imageLoader: ImageLoader,
    private var itemClickListener: OnItemClickListener<MainItem>? = null
) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    private var data: List<MainItem> = mutableListOf()
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

    fun updateList(newData: List<MainItem>) {
        data = newData
        notifyDataSetChanged()
    }

    fun getList(): List<MainItem> {
        return data.toList()
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
                imageLoader.loadImage(mainItem.weatherImage, mainRecyclerImage)
                minMaxMain.text = mainItem.minMaxTemp
            }
        }
    }
}


interface OnItemClickListener<T> {
    fun onItemClick(item: T)
}