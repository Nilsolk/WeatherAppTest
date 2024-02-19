package ru.nilsolk.weatherapptest.data_source.cloud_data_source.models

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import ru.nilsolk.weatherapptest.data_source.cloud_data_source.BaseResponse

class NetworkRequest {
    suspend fun <T> sendRequest(request: suspend () -> Response<T>): BaseResponse<T> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val response = request()
                if (response.isSuccessful) {
                    BaseResponse.Success(response.body()!!)
                } else BaseResponse.Error(response.errorBody()?.string().toString())

            } catch (e: Exception) {
                BaseResponse.Error(e.message.toString())
            }
        }
}