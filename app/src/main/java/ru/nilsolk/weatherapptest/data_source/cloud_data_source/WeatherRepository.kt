package ru.nilsolk.weatherapptest.data_source.cloud_data_source

import ru.nilsolk.weatherapptest.data_source.cloud_data_source.models.DailyForecastModel
import ru.nilsolk.weatherapptest.data_source.cloud_data_source.models.Location
import ru.nilsolk.weatherapptest.data_source.cloud_data_source.models.LocationModel

interface WeatherRepository {
    suspend fun searchLocation(location: String): BaseResponse<List<LocationModel>>

    suspend fun getDailyForecast(location: String, days: Int): BaseResponse<DailyForecastModel>
}