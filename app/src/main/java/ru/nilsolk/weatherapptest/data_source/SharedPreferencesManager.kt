package ru.nilsolk.weatherapptest.data_source

import android.content.Context
import androidx.fragment.app.Fragment

class SharedPreferencesManager(
    private val fragment: Fragment
) {
    fun getCachedLocation(): String? {
        val sharedPreferences =
            fragment.requireContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_CACHED_LOCATION, null)
    }

    fun setCachedLocation(location: String) {
        val sharedPreferences = fragment.requireContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(KEY_CACHED_LOCATION, location).apply()
    }

    companion object {
        private const val PREF_NAME = "fragment_preferences"
        private const val KEY_CACHED_LOCATION = "cached_location"
    }
}
