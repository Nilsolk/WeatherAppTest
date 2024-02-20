package ru.nilsolk.weatherapptest.ui.location

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.nilsolk.weatherapptest.data_source.cloud_data_source.BaseResponse
import ru.nilsolk.weatherapptest.data_source.cloud_data_source.WeatherRepository
import androidx.lifecycle.ViewModel
import ru.nilsolk.weatherapptest.data_source.cloud_data_source.models.LocationModel

class LocationViewModel(private val repository: WeatherRepository) : ViewModel() {
    private val _locations: MutableStateFlow<BaseResponse<List<LocationModel>>?> =
        MutableStateFlow(null)
    val locations = _locations.asStateFlow()

    fun searchLocation(query: String) {
        viewModelScope.launch {
            _locations.value = BaseResponse.Loading
            val response = repository.searchLocation(query)
            _locations.value = response
        }
    }
}