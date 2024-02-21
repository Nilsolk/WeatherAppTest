package ru.nilsolk.weatherapptest.data_source.cloud_data_source

sealed class BaseResponse<out T> {
    class Success<T>(private val value: T) : BaseResponse<T>() {
        fun getData() = value
    }

    class Error(private val error: String) : BaseResponse<Nothing>() {
        fun getError() = error
    }

}