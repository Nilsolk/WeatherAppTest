package ru.nilsolk.weatherapptest.data_source.cloud_data_source.models

import com.google.gson.annotations.SerializedName

data class LocationModel(
    @SerializedName("name") val name: String,
    @SerializedName("region") val region: String,
    @SerializedName("country") val country: String,
    @SerializedName("url") val url: String
)