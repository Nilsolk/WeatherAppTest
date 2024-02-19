package ru.nilsolk.weatherapptest.ui.weather_info

import android.health.connect.datatypes.units.Temperature
import androidx.annotation.DrawableRes

data class InfoItem(val temperature: String, val time: String, @DrawableRes val image: Int)
