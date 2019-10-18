package com.helicoptera.weatherforecast.data.repository

import androidx.lifecycle.LiveData
import com.helicoptera.weatherforecast.data.db.CurrentWeatherDao
import com.helicoptera.weatherforecast.data.network.WeatherNetworkDataSource
import com.helicoptera.weatherforecast.data.network.response.CurrentWeatherResponse
import com.helicoptera.weatherforecast.data.unitlocalized.UnitSpecificCurrentWeatherEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime

class ForecastRepositoryImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource
) : ForecastRepository {

    init{
        weatherNetworkDataSource.downloadedCurrentWeather.observeForever{newCurrentWeather ->
            persistFetchedCurrentWeather(newCurrentWeather)
        }
    }

    private fun persistFetchedCurrentWeather(fetchedWeather: CurrentWeatherResponse){
        GlobalScope.launch(Dispatchers.IO ) {
            if (fetchedWeather.currentWeatherEntry!=null)
            currentWeatherDao.upsert(fetchedWeather.currentWeatherEntry)
        }
    }

    override suspend fun getCurrentWeather(units: String): LiveData<out UnitSpecificCurrentWeatherEntry> {
        return withContext(Dispatchers.IO){
            initWeatherData(units)
            return@withContext currentWeatherDao.getWeatherUnit()
        }
    }

    private suspend fun initWeatherData(units: String){
        if (isFetchCurrentNeeded(ZonedDateTime.now().minusMinutes(60))){
            fetchCurrentWeather(units)
        }
    }

    private suspend fun fetchCurrentWeather(units: String){
        weatherNetworkDataSource.fetchCurrentWeather(
            "Moscow", units
        )
    }

    private fun isFetchCurrentNeeded(lastFetchTime: ZonedDateTime): Boolean{
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }
}