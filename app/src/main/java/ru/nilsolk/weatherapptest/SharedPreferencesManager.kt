package ru.nilsolk.weatherapptest

import android.content.Context

object SharedPreferencesManager {
    private const val PREF_NAME = "fragment_preferences"
    private const val KEY_CACHED_LOCATION = "cached_location"

    fun getCachedLocation(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_CACHED_LOCATION, null)
    }

    fun setCachedLocation(context: Context, location: String) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(KEY_CACHED_LOCATION, location).apply()
    }
}
