package ru.nilsolk.weatherapptest.ui.main_weather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.nilsolk.weatherapptest.R

class MainWeatherFragment : Fragment() {

    companion object {
        fun newInstance() = MainWeatherFragment()
    }

    private lateinit var viewModel: MainWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_weather, container, false)
    }


}