package com.helicoptera.weatherforecast.data.provider

import com.helicoptera.weatherforecast.data.db.entity.WeatherLocation

class LocationProviderImpl : LocationProvider {
    override suspend fun hasLocationChanged(lastWeatherLocation: WeatherLocation): Boolean {
        return true
    }

    override suspend fun getPreferredLocationString(): String {
        return "Minsk"
    }
}