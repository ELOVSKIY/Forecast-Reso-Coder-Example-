package com.helicoptera.weatherforecast.ui.weather.current

import androidx.lifecycle.ViewModel
import com.helicoptera.weatherforecast.data.provider.UnitProvider
import com.helicoptera.weatherforecast.data.repository.ForecastRepository
import com.helicoptera.weatherforecast.internal.UnitSystem
import com.helicoptera.weatherforecast.internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository,
    private val unitProvider: UnitProvider
) : ViewModel() {
    private val unitSystem = unitProvider.getUnitSystem()

    val isMetric: Boolean
        get() = unitProvider.getUnitSystem() == UnitSystem.METRIC


    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather(unitSystem)
    }

    val weatherLocation by lazyDeferred{
        forecastRepository.getWeatherLocation()
    }

}
