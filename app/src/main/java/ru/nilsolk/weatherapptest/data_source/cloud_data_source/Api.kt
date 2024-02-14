package ru.nilsolk.weatherapptest.data_source.cloud_data_source

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.nilsolk.weatherapptest.data_source.cloud_data_source.models.DailyForecastModel
import ru.nilsolk.weatherapptest.data_source.cloud_data_source.models.LocationModel

interface Api {
    @GET("search.json")
    suspend fun searchLocation(
        @Query("q") location: String
    ): Response<List<LocationModel>>

    @GET("forecast.json")
    suspend fun getDailyForecast(
        @Query("q") location: String,
        @Query("days") days: Int
    ): Response<DailyForecastModel>
}