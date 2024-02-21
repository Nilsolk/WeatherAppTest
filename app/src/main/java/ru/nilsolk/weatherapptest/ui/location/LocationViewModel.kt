package ru.nilsolk.weatherapptest.ui.location

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.nilsolk.weatherapptest.data_source.cloud_data_source.BaseResponse
import ru.nilsolk.weatherapptest.data_source.cloud_data_source.WeatherRepository
import ru.nilsolk.weatherapptest.data_source.cloud_data_source.models.LocationModel
import ru.nilsolk.weatherapptest.data_source.cloud_data_source.models.WeatherRepositoryImpl

class LocationViewModel(private val repository: WeatherRepository) : ViewModel() {

    private val _locations: MutableStateFlow<BaseResponse<List<LocationModel>>?> =
        MutableStateFlow(null)
    val locations = _locations.asStateFlow()

    fun searchLocation(query: String) {
        viewModelScope.launch {
            try {
                val response = repository.searchLocation(query)
                _locations.value = response
            } catch (e: Exception) {
                _locations.value = null
                Log.e("LocationViewModel", "Error searching location: ${e.message}", e)
            }
        }
    }
}