package ru.nilsolk.weatherapptest.ui

import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class Navigation(
    private val fragment: Fragment
) {
    fun backNavigate() {
        fragment.findNavController().popBackStack()
    }

    fun navigateTo(@IdRes path: Int) {
        fragment.findNavController().navigate(path)
    }

    fun <T> navigateWithData(data: T, @IdRes path: Int, key: String) {
        val bundle = Bundle().apply {
            when (data) {
                is String -> putString(key, data)
                is Parcelable -> putParcelable(key, data)
            }
        }
        fragment.findNavController().navigate(path, bundle)
    }
}