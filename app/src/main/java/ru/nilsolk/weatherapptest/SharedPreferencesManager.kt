package ru.nilsolk.weatherapptest

import android.content.Context

object SharedPreferencesManager {
    private const val PREF_NAME = "fragment_preferences"
    private const val KEY_FIRST_RUN = "first_run"

    fun isFirstRun(context: Context, fragmentTag: String): Boolean {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("${KEY_FIRST_RUN}_$fragmentTag", true)
    }

    fun setFirstRun(context: Context, fragmentTag: String, isFirstRun: Boolean) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("${KEY_FIRST_RUN}_$fragmentTag", isFirstRun).apply()
    }
}
