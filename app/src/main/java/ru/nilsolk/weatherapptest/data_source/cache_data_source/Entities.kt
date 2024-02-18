package ru.nilsolk.weatherapptest.data_source.cache_data_source

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "locations")
data class Location(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val region: String,
    val url: String
)

@Entity(tableName = "days_weather")
data class Weather(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val locationId: Int,
    val day: String,
    val maxTemperature: Int,
    val minTemperature: Int,
    val temperature: Int,
    val windSpeed: Float,
    val humidity: Int,
    val icon: Int
)

@Entity(tableName = "hourly_weather")
data class HourlyWeather(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val locationId: Int,
    val time: String,
    val temperature: Int,
    val icon: String
)
