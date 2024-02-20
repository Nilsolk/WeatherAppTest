package ru.nilsolk.weatherapptest.ui.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import ru.nilsolk.weatherapptest.R
import ru.nilsolk.weatherapptest.data_source.cloud_data_source.BaseResponse
import ru.nilsolk.weatherapptest.databinding.FragmentLocationBinding
import ru.nilsolk.weatherapptest.ui.main_weather.OnItemClickListener

class LocationFragment : Fragment(), OnItemClickListener<LocationItem> {

    private lateinit var viewModel: LocationViewModel
    private lateinit var binding: FragmentLocationBinding
    private lateinit var locationAdapter: LocationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[LocationViewModel::class.java]

        locationAdapter = LocationAdapter(mutableListOf())
        locationAdapter.setOnItemClickListener(this)
        binding.citiesRecycler.apply {
            adapter = locationAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.locations.collect { response ->
                when (response) {
                    is BaseResponse.Loading -> {
                        // Показать индикатор загрузки
                    }

                    is BaseResponse.Success -> {
                        // Обновить список местоположений
                        val locationItems = response.getData().map { location ->
                            LocationItem(location.name, location.country)
                        }
                        locationAdapter.updateList(locationItems)
                    }

                    is BaseResponse.Error -> {
                        // Обработать ошибку
                        Toast.makeText(
                            requireContext(),
                            "Error: ${response.getError()}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    else -> {}
                }
            }
        }
    }

    override fun onItemClick(item: LocationItem) {
        findNavController().navigate(R.id.action_searchFragment_to_mainFragment)
    }
}

