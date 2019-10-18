package com.helicoptera.weatherforecast.data.network

import androidx.lifecycle.LiveData
import com.helicoptera.weatherforecast.data.network.response.CurrentWeatherResponse

interface WeatherNetworkDataSource {
    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(
        location: String,
        units: String = "m"
    )
}