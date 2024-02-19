package ru.nilsolk.weatherapptest.ui.main_weather

import androidx.annotation.DrawableRes

data class MainItem(
    val weekday: String,
    @DrawableRes val weatherImage: Int,
    val weatherNow: String,
    val minMaxTemp: String
)