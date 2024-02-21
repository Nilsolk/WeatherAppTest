package ru.nilsolk.weatherapptest.data_source.cloud_data_source.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable


data class DailyForecastModel(

    @SerializedName("location") val location: Location,
    @SerializedName("current") val current: Current,
    @SerializedName("forecast") val forecast: Forecast

)

data class Forecast(
    @SerializedName("forecastday") val forecastDay: ArrayList<Forecastday> = arrayListOf()
)

data class Location(

    @SerializedName("name") val name: String,
    @SerializedName("region") val region: String,
    @SerializedName("country") val country: String,
    @SerializedName("localtime") val localtime: String

)


data class Current(
    @SerializedName("last_updated") val lastUpdated: String,
    @SerializedName("temp_c") val tempC: Int,
    @SerializedName("is_day") val isDay: Int,
    @SerializedName("condition") val condition: Condition,
    @SerializedName("wind_kph") val windKph: Double,
    @SerializedName("humidity") val humidity: Int,
)

@kotlinx.parcelize.Parcelize
data class Condition(

    @SerializedName("text") val text: String,
    @SerializedName("icon") val icon: String,

    ) : Parcelable

@kotlinx.parcelize.Parcelize
data class Day(

    @SerializedName("maxtemp_c") val maxTempC: Double,
    @SerializedName("mintemp_c") val minTempC: Double,
    @SerializedName("avgtemp_c") val avgTempC: Double,
    @SerializedName("maxwind_kph") val maxWindKph: Double,
    @SerializedName("avghumidity") val avgHumidity: Int,
    @SerializedName("condition") val condition: Condition,

    ) : Parcelable

@kotlinx.parcelize.Parcelize
data class Hour(

    @SerializedName("time") val time: String,
    @SerializedName("temp_c") val tempC: Double,
    @SerializedName("is_day") val isDay: Int,
    @SerializedName("condition") val condition: Condition,
    @SerializedName("wind_kph") val windKph: Double,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("cloud") val cloud: Int,

    ) : Parcelable

@kotlinx.parcelize.Parcelize
data class Forecastday(
    @SerializedName("date") val date: String,
    @SerializedName("date_epoch") val dateEpoch: Int,
    @SerializedName("day") val day: Day,
    @SerializedName("hour") val hour: List<Hour>

) : Parcelable