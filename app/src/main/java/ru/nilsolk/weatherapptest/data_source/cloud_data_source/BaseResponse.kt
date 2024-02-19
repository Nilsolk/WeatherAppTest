package ru.nilsolk.weatherapptest.data_source.cloud_data_source

sealed class BaseResponse<out T> {
    class Success<T>(value: T) : BaseResponse<T>()
    class Error(error: String) : BaseResponse<Nothing>()
    data object Loading : BaseResponse<Nothing>()
}