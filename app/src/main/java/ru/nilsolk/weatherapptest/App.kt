package ru.nilsolk.weatherapptest

import android.app.Application
import androidx.room.Room
import ru.nilsolk.weatherapptest.data_source.cache_data_source.WeatherDatabase

class App : Application() {
    private lateinit var weatherDatabase: WeatherDatabase

    override fun onCreate() {
        super.onCreate()
//        weatherDatabase = Room.databaseBuilder(
//            this,
//            WeatherDatabase::class.java,
//            "weather_database"
//        ).build()
    }

}