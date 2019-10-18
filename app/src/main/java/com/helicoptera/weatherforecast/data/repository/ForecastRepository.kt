package com.helicoptera.weatherforecast.data.repository

import androidx.lifecycle.LiveData
import com.helicoptera.weatherforecast.data.unitlocalized.UnitSpecificCurrentWeatherEntry

interface ForecastRepository {

    suspend fun getCurrentWeather()
            : LiveData<out UnitSpecificCurrentWeatherEntry>
}