package ru.nilsolk.weatherapptest

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesManager {
    private const val PREF_NAME = "WeatherAppPrefs"
    private const val KEY_SELECTED_LOCATION = "selected_location"


    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }


    fun saveSelectedLocation(context: Context, location: String) {
        val editor = getSharedPreferences(context).edit()
        editor.putString(KEY_SELECTED_LOCATION, location)
        editor.apply()
    }

    fun getSelectedLocation(context: Context): String? {
        return getSharedPreferences(context).getString(KEY_SELECTED_LOCATION, null)
    }


    fun isLocationSelected(context: Context): Boolean {
        return getSharedPreferences(context).contains(KEY_SELECTED_LOCATION)
    }

    fun clearSelectedLocation(context: Context) {
        val editor = getSharedPreferences(context).edit()
        editor.remove(KEY_SELECTED_LOCATION)
        editor.apply()
    }
}