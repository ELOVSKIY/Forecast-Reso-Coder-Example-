package com.helicoptera.weatherforecast.ui.weather.current

import androidx.lifecycle.ViewModel
import com.helicoptera.weatherforecast.data.repository.ForecastRepository
import com.helicoptera.weatherforecast.internal.UnitSystem
import com.helicoptera.weatherforecast.internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository
) : ViewModel() {
    private val unitSystem = UnitSystem.METRIC

    val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC


    val weather by lazyDeferred() {
        forecastRepository.getCurrentWeather(unitSystem)
    }

}
