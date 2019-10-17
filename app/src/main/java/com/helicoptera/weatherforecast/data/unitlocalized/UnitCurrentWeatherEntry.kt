package com.helicoptera.weatherforecast.data.unitlocalized

data class UnitCurrentWeatherEntry(
    override val feelslike: Int,
    override val isDay: String,
    override val precip: Int,
    override val temperature: Int,
    override val weatherCode: Int,
    override val visibility: Int,
    override val weatherDescriptions: List<String>,
    override val weatherIcons: List<String>,
    override val windDir: String,
    override val windSpeed: Int
) : UnitSpecificCurrentWeatherEntry