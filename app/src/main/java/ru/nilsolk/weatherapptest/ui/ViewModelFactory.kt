package ru.nilsolk.weatherapptest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.nilsolk.weatherapptest.data_source.cloud_data_source.WeatherRepository
import ru.nilsolk.weatherapptest.ui.location.LocationViewModel
import ru.nilsolk.weatherapptest.ui.main_weather.MainWeatherViewModel


class ViewModelFactory(private val repository: WeatherRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return when {
            modelClass.isAssignableFrom(MainWeatherViewModel::class.java) ->
                MainWeatherViewModel(repository) as T

            modelClass.isAssignableFrom(LocationViewModel::class.java) ->
                LocationViewModel(repository) as T

            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

