package ru.nilsolk.weatherapptest.data_source.cache_data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.nilsolk.weatherapptest.data_source.cache_data_source.dao.HourlyWeatherDAO
import ru.nilsolk.weatherapptest.data_source.cache_data_source.dao.LocationDAO
import ru.nilsolk.weatherapptest.data_source.cache_data_source.dao.WeatherDAO

@Database(entities = [Location::class, HourlyWeather::class, Weather::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationDAO
    abstract fun hourlyWeatherDao(): HourlyWeatherDAO
    abstract fun weatherDao(): WeatherDAO
}