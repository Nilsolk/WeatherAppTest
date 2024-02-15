package ru.nilsolk.weatherapptest.ui.weather_info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.nilsolk.weatherapptest.R

class WeatherInfoFragment : Fragment() {

    companion object {
        fun newInstance() = WeatherInfoFragment()
    }

    private lateinit var viewModel: WeatherInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather_info, container, false)
    }


}