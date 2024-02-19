package ru.nilsolk.weatherapptest.data_source.cloud_data_source.models


import ru.nilsolk.weatherapptest.data_source.cloud_data_source.Api
import ru.nilsolk.weatherapptest.data_source.cloud_data_source.BaseResponse
import ru.nilsolk.weatherapptest.data_source.cloud_data_source.WeatherRepository

class WeatherRepositoryImpl(
    private val api: Api,
    private val networkRequest: NetworkRequest
) :
    WeatherRepository {
    override suspend fun searchLocation(location: String): BaseResponse<List<LocationModel>> {
        return networkRequest.sendRequest {
            api.searchLocation(location)
        }
    }

    override suspend fun getDailyForecast(
        location: String,
        days: Int
    ): BaseResponse<DailyForecastModel> {
        return networkRequest.sendRequest {
            api.getDailyForecast(location, days)
        }
    }

}

