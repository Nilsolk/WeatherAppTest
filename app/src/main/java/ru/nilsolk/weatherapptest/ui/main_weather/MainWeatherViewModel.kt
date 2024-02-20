package ru.nilsolk.weatherapptest.ui.main_weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.nilsolk.weatherapptest.data_source.cloud_data_source.BaseResponse
import ru.nilsolk.weatherapptest.data_source.cloud_data_source.WeatherRepository
import ru.nilsolk.weatherapptest.data_source.cloud_data_source.models.DailyForecastModel

class MainWeatherViewModel(private val repository: WeatherRepository) : ViewModel() {
    private val _dailyForecast: MutableStateFlow<BaseResponse<DailyForecastModel>> =
        MutableStateFlow(BaseResponse.Loading)
    val dailyForecast = _dailyForecast.asStateFlow()


    fun getDailyForecast(location: String, days: Int) {
        viewModelScope.launch {
            repository.getDailyForecast(location, days).also { data ->
                _dailyForecast.update { data }
            }
        }
    }
}