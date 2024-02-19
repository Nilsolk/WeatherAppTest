package ru.nilsolk.weatherapptest.ui.main_weather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.nilsolk.weatherapptest.databinding.FragmentMainWeatherBinding

class MainWeatherFragment : Fragment(), OnItemClickListener<MainItem> {

    private lateinit var binding: FragmentMainWeatherBinding
    private lateinit var mainAdapter: MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainWeatherBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainAdapter = MainAdapter(mutableListOf())
        mainAdapter.setOnItemClickListener(this)
        binding.mainRecyclerView.adapter = mainAdapter
    }

    override fun onItemClick(item: MainItem) {

    }


}