package ru.nilsolk.weatherapptest.ui.main_weather

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import ru.nilsolk.weatherapptest.App
import ru.nilsolk.weatherapptest.data_source.ImageLoader
import ru.nilsolk.weatherapptest.ui.Navigation
import ru.nilsolk.weatherapptest.R
import ru.nilsolk.weatherapptest.data_source.SharedPreferencesManager
import ru.nilsolk.weatherapptest.data_source.cloud_data_source.BaseResponse
import ru.nilsolk.weatherapptest.data_source.cloud_data_source.models.DailyForecastModel
import ru.nilsolk.weatherapptest.databinding.FragmentMainWeatherBinding
import ru.nilsolk.weatherapptest.ui.ViewModelFactory


class MainWeatherFragment : Fragment(), OnItemClickListener<MainItem> {

    private lateinit var viewModel: MainWeatherViewModel
    private lateinit var binding: FragmentMainWeatherBinding
    private lateinit var mainAdapter: MainAdapter
    private lateinit var imageLoader: ImageLoader
    private lateinit var forecast: DailyForecastModel
    private lateinit var navigation: Navigation
    private lateinit var sharedPreferencesManager: SharedPreferencesManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val application = requireActivity().application as App
        val repository = application.provideWeatherRepository()
        viewModel =
            ViewModelProvider(this, ViewModelFactory(repository))[MainWeatherViewModel::class.java]
        navigation = Navigation(this)
        sharedPreferencesManager = SharedPreferencesManager(this)
        imageLoader = ImageLoader(requireContext())
        mainAdapter = MainAdapter(imageLoader)
        mainAdapter.setOnItemClickListener(this)
        binding.mainRecyclerView.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        val cachedLocation = sharedPreferencesManager.getCachedLocation()
        if (cachedLocation.isNullOrEmpty()) {
            binding.mainRecyclerView.visibility = View.GONE
            binding.cardTemp.visibility = View.VISIBLE
            binding.cardToday.visibility = View.GONE
        } else {
            binding.mainRecyclerView.visibility = View.VISIBLE
            binding.cardTemp.visibility = View.GONE
            binding.cardToday.visibility = View.VISIBLE

            viewModel.getDailyForecast(cachedLocation, 2)
        }

        observeViewModel()

        binding.cardToday.setOnClickListener {
            navigation.navigateWithData(
                forecast.forecast.forecastDay[0],
                R.id.action_mainFragment_to_infoFragment,
                "weather_day"
            )
        }
        binding.anotherLocation.setOnClickListener {
            navigation.navigateTo(R.id.from_mainFragment_to_locationFragmrnt)
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.dailyForecast.collect { response ->
                when (response) {
                    is BaseResponse.Success -> {
                        binding.mainProgressBar.visibility = View.INVISIBLE
                        forecast = response.getData()
                        imageLoader.loadImage(
                            forecast.current.condition.icon, binding.weatherImage
                        )
                        binding.airText.text = "${forecast.current.windKph} km/h"
                        binding.humidityText.text = "${forecast.current.humidity} %"
                        binding.thermometerGainText.text =
                            "${forecast.forecast.forecastDay[0].day.maxTempC}°"
                        binding.thermometerLossText.text =
                            "${forecast.forecast.forecastDay[0].day.minTempC}°"
                        binding.textView.text = "${forecast.current.tempC}°"
                        binding.location.text = forecast.location.name

                        var daysList = forecast.forecast.forecastDay
                        daysList = ArrayList(daysList.subList(0, daysList.size))
                        mainAdapter.updateList(daysList.map {
                            MainItem(
                                it.day.condition.icon,
                                it.day.condition.text,
                                "${it.day.maxTempC}° / ${it.day.minTempC}°"
                            )
                        })
                    }

                    is BaseResponse.Loading -> {
                        binding.mainProgressBar.visibility = View.VISIBLE
                    }

                    is BaseResponse.Error -> {
                        Log.e("error", response.getError())
                        Log.e("error", sharedPreferencesManager.getCachedLocation().toString())
                        Toast.makeText(
                            requireContext(), "Error: ${response.getError()}", Toast.LENGTH_SHORT
                        ).show()
                    }


                    else -> {}
                }
            }
        }
    }

    override fun onItemClick(item: MainItem) {
        val index = mainAdapter.getList().indexOf(item)
        navigation.navigateWithData(
            forecast.forecast.forecastDay[index],
            R.id.action_mainFragment_to_infoFragment,
            "weather_day"
        )
    }

}


