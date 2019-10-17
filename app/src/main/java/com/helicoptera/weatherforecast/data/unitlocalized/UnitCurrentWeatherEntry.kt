package com.helicoptera.weatherforecast.data.unitlocalized

data class UnitCurrentWeatherEntry(
    override val feelslike: Double,
    override val isDay: String,
    override val precip: Double,
    override val temperature: Double,
    override val weatherCode: Double,
    override val visibility: Double,
    override val weatherDescriptions: List<String>,
    override val weatherIcons: List<String>,
    override val windDir: String,
    override val windSpeed: Double
) : UnitSpecificCurrentWeatherEntry