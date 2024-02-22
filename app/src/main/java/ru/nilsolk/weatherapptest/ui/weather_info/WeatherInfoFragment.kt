package ru.nilsolk.weatherapptest.ui.weather_info

import WeatherInfoViewModel
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.nilsolk.weatherapptest.ImageLoader
import ru.nilsolk.weatherapptest.Navigation
import ru.nilsolk.weatherapptest.data_source.cloud_data_source.models.Forecastday
import ru.nilsolk.weatherapptest.databinding.FragmentWeatherInfoBinding

class WeatherInfoFragment : Fragment() {

    private lateinit var binding: FragmentWeatherInfoBinding
    private lateinit var infoAdapter: InfoAdapter
    private lateinit var viewModel: WeatherInfoViewModel
    private lateinit var navigation: Navigation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[WeatherInfoViewModel::class.java]
        arguments?.getParcelable("weather_day", Forecastday::class.java)
            ?.let { viewModel.setForecast(it) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigation = Navigation(this)
        val imageLoader = ImageLoader(requireContext())
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewHourly.layoutManager = layoutManager
        infoAdapter = InfoAdapter(imageLoader)
        binding.recyclerViewHourly.adapter = infoAdapter

        viewModel.forecast.observe(viewLifecycleOwner) { forecast ->
            imageLoader.loadImage(forecast.day.condition.icon, binding.infoWeatherImage)
            binding.todayDate.text = "${forecast.date} | ${forecast.day.condition.text}"
            binding.windText.text = "${forecast.day.maxWindKph} km/h"
            binding.humidityInfoText.text = "${forecast.day.avgHumidity} %"
            binding.avgTempText.text = "${forecast.day.avgTempC}°"
            binding.maxTempText.text = "${forecast.day.maxTempC}°"
            binding.minTempText.text = "${forecast.day.minTempC}°"

            binding.backArrowInformation.setOnClickListener {
                navigation.backNavigate()
            }

            infoAdapter.updateList(forecast.hour.map {
                InfoItem("${it.tempC}°", it.time.takeLast(5), it.condition.icon)
            })
        }
    }
}
