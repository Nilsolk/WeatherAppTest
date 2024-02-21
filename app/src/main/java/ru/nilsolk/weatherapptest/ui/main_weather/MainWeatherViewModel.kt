package ru.nilsolk.weatherapptest.ui.main_weather

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.nilsolk.weatherapptest.data_source.cloud_data_source.BaseResponse
import ru.nilsolk.weatherapptest.data_source.cloud_data_source.WeatherRepository
import ru.nilsolk.weatherapptest.data_source.cloud_data_source.models.DailyForecastModel


class MainWeatherViewModel(private val repository: WeatherRepository) : ViewModel() {

    private val _dailyForecast = MutableStateFlow<BaseResponse<DailyForecastModel>?>(null)
    val dailyForecast = _dailyForecast.asStateFlow()

    fun getDailyForecast(location: String, days: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getDailyForecast(location, days)
                _dailyForecast.value = response
            } catch (e: Exception) {
                _dailyForecast.value = null
                Log.e("MainWeatherViewModel", "Error fetching daily forecast: ${e.message}", e)
            }
        }
    }
}