package ru.nilsolk.weatherapptest.ui.weather_info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ru.nilsolk.weatherapptest.databinding.FragmentWeatherInfoBinding

class WeatherInfoFragment : Fragment() {

    private lateinit var binding: FragmentWeatherInfoBinding
    private lateinit var infoAdapter: InfoAdapter
    private val infoItems: MutableList<InfoItem> =
        mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewHourly.layoutManager = LinearLayoutManager(requireContext())
        infoAdapter = InfoAdapter(infoItems)
        binding.recyclerViewHourly.adapter = infoAdapter


    }

}