package ru.nilsolk.weatherapptest.ui.location

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import ru.nilsolk.weatherapptest.App
import ru.nilsolk.weatherapptest.R
import ru.nilsolk.weatherapptest.data_source.cloud_data_source.BaseResponse
import ru.nilsolk.weatherapptest.data_source.cloud_data_source.models.NetworkRequest
import ru.nilsolk.weatherapptest.data_source.cloud_data_source.models.WeatherRepositoryImpl
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

        val application = (activity?.application as App)
        val networkRequest = NetworkRequest()
        val repository = WeatherRepositoryImpl(application.provideWeatherApi(), networkRequest)
        viewModel = LocationViewModel(repository)

        locationAdapter = LocationAdapter(mutableListOf())
        locationAdapter.setOnItemClickListener(this)
        binding.citiesRecycler.apply {
            adapter = locationAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        observeViewModel()
        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                p0?.let {
                    val text = it.toString()
                    viewModel.searchLocation(text)
                }
            }

        })
    }


    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.locations.collect { response ->
                when (response) {
                    is BaseResponse.Loading -> {}

                    is BaseResponse.Success -> {
                        val locationItems = response.getData().map { location ->
                            LocationItem(location.name, location.country)
                        }
                        locationAdapter.updateList(locationItems)
                    }

                    is BaseResponse.Error -> {
                        Toast.makeText(
                            requireContext(),
                            "Error to find location",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    else -> {
                        Toast.makeText(
                            requireContext(),
                            "Connection error",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    override fun onItemClick(item: LocationItem) {
        findNavController().navigate(R.id.action_locationFragment_to_mainFragment)
    }
}

