package ru.nilsolk.weatherapptest.ui.location

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import ru.nilsolk.weatherapptest.App
import ru.nilsolk.weatherapptest.R
import ru.nilsolk.weatherapptest.data_source.cloud_data_source.BaseResponse
import ru.nilsolk.weatherapptest.databinding.FragmentLocationBinding
import ru.nilsolk.weatherapptest.ui.ViewModelFactory
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

        val application = requireActivity().application as App
        val repository = application.provideWeatherRepository()
        viewModel =
            ViewModelProvider(this, ViewModelFactory(repository))[LocationViewModel::class.java]

        locationAdapter = LocationAdapter()
        locationAdapter.setOnItemClickListener(this)
        binding.citiesRecycler.apply {
            adapter = locationAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString().isBlank()) {
                    locationAdapter.updateList(emptyList())
                } else {
                    p0?.let {
                        val location = it.toString()
                        viewModel.searchLocation(location)
                        observeViewModel()
                    }
                }
            }

        })
    }


    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.locations.collect { response ->
                when (response) {

                    is BaseResponse.Success -> {
                        val locations = response.getData()
                        if (locations.isEmpty()) {
                            Toast.makeText(
                                requireContext(),
                                "There is no such city",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            locationAdapter.updateList(locations.map { location ->
                                LocationItem(location.name, location.country)
                            })
                        }
                    }

                    is BaseResponse.Error -> {
                        Toast.makeText(
                            requireContext(),
                            "Internet connection error",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    else -> {
                    }
                }
            }
        }
    }

    override fun onItemClick(item: LocationItem) {
        val bundle = Bundle().apply {
            putString("location", "${item.city} ${item.country}")
        }
        findNavController().navigate(R.id.action_locationFragment_to_mainFragment, bundle)
    }
}

