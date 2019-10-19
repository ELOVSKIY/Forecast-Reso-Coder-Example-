package com.helicoptera.weatherforecast.data.network.response


import com.google.gson.annotations.SerializedName
import com.helicoptera.weatherforecast.data.db.entity.CurrentWeatherEntry
import com.helicoptera.weatherforecast.data.db.entity.WeatherLocation
import com.helicoptera.weatherforecast.data.db.entity.Request

data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry,
    val location: WeatherLocation,
    val request: Request
)