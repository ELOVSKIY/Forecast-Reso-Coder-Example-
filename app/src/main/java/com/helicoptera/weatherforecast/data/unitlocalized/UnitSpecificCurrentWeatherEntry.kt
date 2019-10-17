package com.helicoptera.weatherforecast.data.unitlocalized

interface UnitSpecificCurrentWeatherEntry{
    val feelslike: Double
    val isDay: String
    val precip: Double
    val temperature: Double
    val weatherCode: Double
    val visibility: Double
    val weatherDescriptions: List<String>
    val weatherIcons: List<String>
    val windDir: String
    val windSpeed: Double
}