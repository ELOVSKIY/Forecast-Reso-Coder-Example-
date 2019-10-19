package com.helicoptera.weatherforecast.data.repository

import androidx.lifecycle.LiveData
import com.helicoptera.weatherforecast.data.db.entity.WeatherLocation
import com.helicoptera.weatherforecast.data.unitlocalized.UnitSpecificCurrentWeatherEntry

interface ForecastRepository {

    suspend fun getCurrentWeather(units: String)
            : LiveData<out UnitSpecificCurrentWeatherEntry>

    suspend fun getWeatherLocation()
            : LiveData<WeatherLocation>
}