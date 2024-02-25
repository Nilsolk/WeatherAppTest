package ru.nilsolk.weatherapptest.ui.weather_info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.nilsolk.weatherapptest.data_source.cloud_data_source.models.Forecastday

class WeatherInfoViewModel : ViewModel() {

    private val _forecast = MutableLiveData<Forecastday>()
    val forecast: LiveData<Forecastday>
        get() = _forecast

    fun setForecast(forecast: Forecastday) {
        _forecast.value = forecast
    }
}