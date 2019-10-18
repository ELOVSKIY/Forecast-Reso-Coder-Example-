package com.helicoptera.weatherforecast.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.helicoptera.weatherforecast.data.WeatherStackApiService
import com.helicoptera.weatherforecast.data.network.response.CurrentWeatherResponse
import com.helicoptera.weatherforecast.internal.NoConnectivityException

class WeatherNetworkDataSourceImpl(
    private val weatherStackApiService: WeatherStackApiService
) : WeatherNetworkDataSource {

    private val _downloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()
    override val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadedCurrentWeather

    override suspend fun fetchCurrentWeather(location: String, units: String) {
        try{
            val fetchCurrentWeather = weatherStackApiService
                .getCurrentWeather(location, units)
                .await()
            _downloadedCurrentWeather.postValue(fetchCurrentWeather)
        }catch (e: NoConnectivityException){
            Log.e("Connectivity", "No internet connection.")
        }
    }
}