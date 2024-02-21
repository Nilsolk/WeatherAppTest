package ru.nilsolk.weatherapptest.ui.main_weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.nilsolk.weatherapptest.R
import ru.nilsolk.weatherapptest.databinding.FragmentMainWeatherBinding

class MainWeatherFragment : Fragment(),
    OnItemClickListener<MainItem> {

    private lateinit var viewModel: MainWeatherViewModel
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

        mainAdapter = MainAdapter(
            mutableListOf(
                MainItem(
                    "String",
                    R.drawable.test_image,
                    "28",
                    "16/17"
                ),
                MainItem(
                    "Stringgg",
                    R.drawable.test_image,
                    "25",
                    "11/12"
                )
            )
        )
        mainAdapter.setOnItemClickListener(this)
        binding.mainRecyclerView.adapter = mainAdapter
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onItemClick(item: MainItem) {
        findNavController().navigate(R.id.action_mainFragment_to_infoFragment)
    }

}
