package ru.nilsolk.weatherapptest.ui.main_weather

import androidx.annotation.DrawableRes
import java.io.Serializable

data class MainItem(
    val weatherImage: String,
    val weatherNow: String,
    val minMaxTemp: String
)